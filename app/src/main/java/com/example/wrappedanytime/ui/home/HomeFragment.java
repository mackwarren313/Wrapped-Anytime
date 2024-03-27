package com.example.wrappedanytime.ui.home;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.wrappedanytime.MainActivity;
import com.example.wrappedanytime.databinding.FragmentHomeBinding;
import com.example.wrappedanytime.spotify.Datatypes.Album;
import com.example.wrappedanytime.spotify.Datatypes.Artist;
import com.example.wrappedanytime.spotify.Datatypes.Track;
import com.example.wrappedanytime.spotify.Datatypes.User;
import com.example.wrappedanytime.spotify.SpotifyData;

import java.io.IOException;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private MediaPlayer mp;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        /*Model of how to use the spotify data getter
         * At the top of your class's onCreateView, put:
         * SpotifyData dataRetriever = new SpotifyData(this.getActivity());
         * Then, later, wherever you want the data, put:
         * dataRetriever.getUser();
         * This will return a User object. More objects coming.
         */
        SpotifyData dataRetriever = new SpotifyData(this.getActivity());
        Artist artist = dataRetriever.getArtist("4j56EQDQu5XnL7R3E9iFJT");
        homeViewModel.setText(artist.toString());
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}