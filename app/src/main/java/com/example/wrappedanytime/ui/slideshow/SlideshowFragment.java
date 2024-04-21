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
import com.example.wrappedanytime.spotify.Audio;
import com.example.wrappedanytime.spotify.Datatypes.Artist;
import com.example.wrappedanytime.spotify.Datatypes.Track;
import com.example.wrappedanytime.spotify.Datatypes.User;
import com.example.wrappedanytime.spotify.Datatypes.UserData;
import com.example.wrappedanytime.spotify.SpotifyData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SlideshowFragment extends Fragment{

    private FragmentSlideshowBinding binding;
    SpotifyData dataRetriever = new SpotifyData(this.getActivity());
    User user = dataRetriever.getUser();
    //UserData data = dataRetriever.getUserData(UserData.TimeRange.MEDIUM, -1);
    UserData data;
    List<Artist> topArtistsData;
    List<Track> topTracksData;
    Date genDate;

    //String Genre = data.getTopGenre();
    String Genre;
    RecyclerView artistView;
    RecyclerView tracksView;
    public SlideshowFragment(UserData ud) {
        data = ud;
        topArtistsData = data.getTopArtists();
        topTracksData = data.getTopTracks();
        Genre = data.getTopGenre();
        genDate = data.getGenDate();
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        topArtistsData = topArtistsData.subList(0,5);

        topTracksData = topTracksData.subList(0,5);

        TextView welcome = root.findViewById(R.id.welcome_with_username);
        welcome.setText("Welcome " + user.getDisplayName());



        TextView topGenre = root.findViewById(R.id.top_genre_tv);
        topGenre.setText(data.getTopGenre());

        TextView dateGenerated = root.findViewById(R.id.date_generated_tv);
        StringBuilder sb = new StringBuilder();
        sb.append(genDate.getMonth() + 1);
        sb.append("/");
        sb.append(genDate.getDate());
        sb.append("/");
        sb.append(genDate.getYear()-100);
        dateGenerated.setText(sb);

        for (int i = 0; i < topTracksData.size(); i++) {
            if (Audio.playAudio(topTracksData.get(i).getPreviewUrl())) break;
        }

        artistView = root.findViewById(R.id.top_artists_list);
        artistView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        artistView.setAdapter(new RecyclerAdapterArtists(getContext().getApplicationContext(), topArtistsData));

        tracksView = root.findViewById(R.id.top_tracks_list);
        tracksView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        tracksView.setAdapter(new RecyclerAdapterTracks(getActivity(), topTracksData));
        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Audio.stopAudio();
        binding = null;
    }
}