package com.example.wrappedanytime.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wrappedanytime.R;
import com.example.wrappedanytime.databinding.FragmentSlideshowBinding;
import com.example.wrappedanytime.spotify.Audio;
import com.example.wrappedanytime.spotify.Datatypes.Artist;
import com.example.wrappedanytime.spotify.Datatypes.Track;
import com.example.wrappedanytime.spotify.Datatypes.User;
import com.example.wrappedanytime.spotify.Datatypes.UserData;
import com.example.wrappedanytime.spotify.SpotifyData;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SlideshowFragment extends Fragment{

    private FragmentSlideshowBinding binding;

    public static UserData data;

    RecyclerView artistView;
    RecyclerView tracksView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        List<Artist> topArtistsData = data.getTopArtists();
        List<Track> topTracksData = data.getTopTracks();

        //Calling from database in here actually

        String Genre = data.getTopGenre();

        ArrayList<String> topArtists = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            topArtists.add(topArtistsData.get(i).getName());
        }

        ArrayList<String> topTracks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            topTracks.add(topTracksData.get(i).getName());
        }

        TextView welcome = root.findViewById(R.id.welcome_with_username);
        //welcome.setText("Welcome " + user.getDisplayName());

        TextView topGenre = root.findViewById(R.id.top_genre_tv);
        topGenre.setText(data.getTopGenre());
        for (int i = 0; i < topTracksData.size(); i++) {
            if (Audio.playAudio(topTracksData.get(i).getPreviewUrl())) break;
        }

        artistView = root.findViewById(R.id.top_artists_list);
        artistView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        artistView.setAdapter(new RecyclerAdapter(getContext().getApplicationContext(), topArtists));

        tracksView = root.findViewById(R.id.top_tracks_list);
        tracksView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        tracksView.setAdapter(new RecyclerAdapter(getContext().getApplicationContext(), topTracks));
        ImageView testing = root.findViewById(R.id.testingImage);
        topArtistsData.get(0).getImage().setImageView(testing);
        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Audio.stopAudio();
        binding = null;
    }
}