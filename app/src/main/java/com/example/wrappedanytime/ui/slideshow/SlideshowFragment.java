package com.example.wrappedanytime.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wrappedanytime.R;
import com.example.wrappedanytime.databinding.FragmentSlideshowBinding;
import com.example.wrappedanytime.spotify.Datatypes.User;

import java.util.ArrayList;
import java.util.List;

public class SlideshowFragment extends Fragment{

    private FragmentSlideshowBinding binding;

    HoldSpotifyTop holdSpotifyTop = new HoldSpotifyTop();
    ArrayList<String> topArtists = holdSpotifyTop.getTopArtists();
    ArrayList <String> topTracks = holdSpotifyTop.getTopTracks();
    String Genre = holdSpotifyTop.getTopGenre();
    RecyclerView artistView;
    RecyclerView tracksView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView welcome = root.findViewById(R.id.welcome_with_username);
        welcome.setText(welcomeSetText());

        TextView minutesListened = root.findViewById(R.id.minutes_listened_tv);
        welcome.setText(minutesSetText());

        TextView topGenre = root.findViewById(R.id.top_genre_tv);
        welcome.setText(genreSetText());



        artistView = root.findViewById(R.id.top_artists_list);
        artistView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        artistView.setAdapter(new RecyclerAdapter(getContext().getApplicationContext(), topArtists));

        tracksView = root.findViewById(R.id.top_tracks_list);
        tracksView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        tracksView.setAdapter(new RecyclerAdapter(getContext().getApplicationContext(), topTracks));

        return root;
    }

    private CharSequence genreSetText() {
        return holdSpotifyTop.getTopGenre();
    }

    private CharSequence minutesSetText() {
        return holdSpotifyTop.getMinutesListened();
    }

    public CharSequence welcomeSetText(){
        return "Welcome " + holdSpotifyTop.getUsername();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}