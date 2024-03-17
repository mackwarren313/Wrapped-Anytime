package com.example.wrappedanytime.ui.home;

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
import com.example.wrappedanytime.spotify.Datatypes.User;
import com.example.wrappedanytime.spotify.SpotifyData;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

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
        User user = dataRetriever.getUser();
        homeViewModel.setText(user.toString());
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}