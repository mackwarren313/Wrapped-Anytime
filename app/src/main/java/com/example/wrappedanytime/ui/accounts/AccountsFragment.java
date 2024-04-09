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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wrappedanytime.MainActivity;
import com.example.wrappedanytime.R;
import com.example.wrappedanytime.databinding.FragmentAccountsBinding;
import com.example.wrappedanytime.spotify.Datatypes.Album;
import com.example.wrappedanytime.spotify.Datatypes.Artist;
import com.example.wrappedanytime.spotify.Datatypes.Track;
import com.example.wrappedanytime.spotify.Datatypes.User;
import com.example.wrappedanytime.spotify.Datatypes.UserData;
import com.example.wrappedanytime.spotify.SpotifyData;
import com.example.wrappedanytime.ui.accounts.AccountsViewModel;
import com.example.wrappedanytime.ui.slideshow.RecyclerAdapter;

import java.io.IOException;
import java.util.ArrayList;


public class AccountsFragment extends Fragment {

    private FragmentAccountsBinding binding;
    RecyclerView accountsListView;
    ArrayList<String> accounts;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AccountsViewModel accountsViewModel =
                new ViewModelProvider(this).get(AccountsViewModel.class);

        binding = FragmentAccountsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        accountsListView = root.findViewById(R.id.top_artists_list);
        accountsListView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        accountsListView.setAdapter(new AccountsRecyclerAdapter(getContext().getApplicationContext(), accounts));

        return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}