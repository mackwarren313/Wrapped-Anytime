package com.example.wrappedanytime.ui.accounts;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;

import com.example.wrappedanytime.MainActivity;
import com.example.wrappedanytime.R;
import com.example.wrappedanytime.databinding.FragmentAccountsBinding;
import com.example.wrappedanytime.firebasewrapper.FirestoreDatabase;
import com.example.wrappedanytime.spotify.Datatypes.Album;
import com.example.wrappedanytime.spotify.Datatypes.Artist;
import com.example.wrappedanytime.spotify.Datatypes.Track;
import com.example.wrappedanytime.spotify.Datatypes.User;
import com.example.wrappedanytime.spotify.Datatypes.UserData;
import com.example.wrappedanytime.spotify.SpotifyData;
import com.example.wrappedanytime.ui.accounts.AccountsViewModel;
import com.example.wrappedanytime.ui.slideshow.RecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;


public class AccountsFragment extends Fragment {
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    String userID;

    private FragmentAccountsBinding binding;
    RecyclerView accountsListView;
    public static ArrayList<UserData> accounts;
    private DatabaseReference database;
    Button add;
    private int editLocation;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AccountsViewModel accountsViewModel =
                new ViewModelProvider(this).get(AccountsViewModel.class);

        binding = FragmentAccountsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        add = root.findViewById(R.id.Add);


        accountsListView = root.findViewById(R.id.accounts_recycler);
        accountsListView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        accountsListView.setAdapter(new AccountsRecyclerAdapter(getContext().getApplicationContext(), accounts));

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                    // User is signed in
                    Log.e("TAG","onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.e("TAG", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };


        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                loadData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e("TAG", "Failed to read value.", error.toException());
            }
        });

        add.setOnClickListener(v -> {
            addPopUpCall(root.getContext());
            accountsListView.getAdapter().notifyDataSetChanged();
        });

        return root;
    }

    private void loadData(DataSnapshot dataSnapshot) {
        accounts.clear();
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            UserData data = new UserData();
            data.setTopArtists(ds.child(userID).getValue(UserData.class).getTopArtists());
            data.setTopTracks(ds.child(userID).getValue(UserData.class).getTopTracks());
            data.setTopGenre(ds.child(userID).getValue(UserData.class).getTopGenre());
            data.setDateAndTime(ds.child(userID).getValue(UserData.class).getDateAndTime());
            accounts.add(data);
        }

    }


    private void addPopUpCall(Context c) {
        AddPopUp dialog = new AddPopUp();
        dialog.show(getFragmentManager(), "dialog");
        dialog.setTargetFragment(AccountsFragment.this, -1);
        editLocation = -1;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}