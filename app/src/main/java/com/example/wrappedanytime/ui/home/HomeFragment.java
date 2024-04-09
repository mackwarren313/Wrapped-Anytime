package com.example.wrappedanytime.ui.home;

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

import com.example.wrappedanytime.MainActivity;
import com.example.wrappedanytime.R;
import com.example.wrappedanytime.databinding.FragmentHomeBinding;
import com.example.wrappedanytime.spotify.Datatypes.Album;
import com.example.wrappedanytime.spotify.Datatypes.Artist;
import com.example.wrappedanytime.spotify.Datatypes.Track;
import com.example.wrappedanytime.spotify.Datatypes.User;
import com.example.wrappedanytime.spotify.Datatypes.UserData;
import com.example.wrappedanytime.spotify.SpotifyData;

import java.io.IOException;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private MediaPlayer mp;
    private String username;
    private String password;
    EditText usernameText;
    EditText passwordText;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        usernameText = root.findViewById(R.id.Username);
        passwordText = root.findViewById(R.id.Password);

        Button createAccount = root.findViewById(R.id.create_account_button);
        Button login = root.findViewById(R.id.login_button);

        login.setOnClickListener(v -> {setLoginInfo();});


//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        /*Model of how to use the spotify data getter
         * At the top of your class's onCreateView, put:
         * SpotifyData dataRetriever = new SpotifyData(this.getActivity());
         * Then, later, wherever you want the data, put:
         * dataRetriever.getUser();
         * This will return a User object. More objects coming.
         */
        SpotifyData dataRetriever = new SpotifyData(this.getActivity());
        //Artist artist = dataRetriever.getArtist("4j56EQDQu5XnL7R3E9iFJT");
        UserData userdata = dataRetriever.getUserData(UserData.TimeRange.MEDIUM);
        homeViewModel.setText(userdata.getTopTracks().toString());
        return root;
    }

    private void setLoginInfo() {
        password = passwordText.getText().toString();
        username = usernameText.getText().toString();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}