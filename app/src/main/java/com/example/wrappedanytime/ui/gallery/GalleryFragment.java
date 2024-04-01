package com.example.wrappedanytime.ui.gallery;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.wrappedanytime.databinding.FragmentGalleryBinding;
import com.example.wrappedanytime.spotify.Datatypes.Track;
import com.example.wrappedanytime.spotify.SpotifyData;

import java.io.IOException;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        SpotifyData dataRetriever = new SpotifyData(this.getActivity());
        Track track = dataRetriever.getTrack("2G9lekfCh83S0lt2yfffBz");
        galleryViewModel.setText(track.toString());
        MediaPlayer mp = new MediaPlayer();
        try {
            mp.setDataSource(track.getPreviewUrl());
            mp.prepare();
            mp.start();
        } catch (IOException ignored) {
        }
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}