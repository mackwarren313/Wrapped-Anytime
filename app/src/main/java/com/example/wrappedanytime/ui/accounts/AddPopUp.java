package com.example.wrappedanytime.ui.accounts;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.wrappedanytime.R;
import com.example.wrappedanytime.spotify.Datatypes.User;
import com.example.wrappedanytime.spotify.Datatypes.UserData;
import com.example.wrappedanytime.spotify.SpotifyData;
import com.example.wrappedanytime.ui.home.HomeFragment;
import com.example.wrappedanytime.ui.slideshow.SlideShowClass;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddPopUp extends DialogFragment {
    String[] timeSpanChoice = {"Long", "Medium", "Short"};
    MaterialCardView selectTimeSpan;
    Button longTime;
    Button mediumTime;
    Button shortTime;
    SpotifyData dataRetriever = new SpotifyData(this.getActivity());
    User user = dataRetriever.getUser();
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private String userID;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.generate_wrapped_popup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();

        longTime = view.findViewById(R.id.long_button);
        mediumTime = view.findViewById(R.id.medium_button);
        shortTime = view.findViewById(R.id.short_button);

        longTime.setOnClickListener(v -> {
            longWrapped();
            dismiss();
        });

        mediumTime.setOnClickListener(v -> {
            mediumWrapped();
            dismiss();
        });

        shortTime.setOnClickListener(v -> {
            shortWrapped();
            dismiss();
        });
    }

    //generate wraps here
    private void shortWrapped() {
        UserData data = dataRetriever.getUserData(UserData.TimeRange.SHORT);
        myRef.child("users").child(userID).setValue(data);
    }

    private void mediumWrapped() {
        UserData data = dataRetriever.getUserData(UserData.TimeRange.MEDIUM);
        myRef.child("users").child(userID).setValue(data);
    }

    private void longWrapped() {
        UserData data = dataRetriever.getUserData(UserData.TimeRange.LONG);
        myRef.child("users").child(userID).setValue(data);
    }


}