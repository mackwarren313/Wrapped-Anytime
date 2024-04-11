package com.example.wrappedanytime.ui.home;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
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
import androidx.navigation.fragment.NavHostFragment;

import com.example.wrappedanytime.MainActivity;
import com.example.wrappedanytime.R;
import com.example.wrappedanytime.databinding.FragmentHomeBinding;
import com.example.wrappedanytime.spotify.Datatypes.Album;
import com.example.wrappedanytime.spotify.Datatypes.Artist;
import com.example.wrappedanytime.spotify.Datatypes.Track;
import com.example.wrappedanytime.spotify.Datatypes.User;
import com.example.wrappedanytime.spotify.Datatypes.UserData;
import com.example.wrappedanytime.spotify.SpotifyData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.spotify.sdk.android.auth.LoginActivity;

import java.io.IOException;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private MediaPlayer mp;
    private String username;
    private String password;
    EditText usernameText;
    EditText passwordText;

    private FirebaseAuth auth;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        auth = FirebaseAuth.getInstance();

        usernameText = root.findViewById(R.id.Username);
        passwordText = root.findViewById(R.id.Password);

        Button createAccount = root.findViewById(R.id.create_account_button);
        Button login = root.findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = passwordText.getText().toString().trim();
                String pass = usernameText.getText().toString().trim();

                if (pass.isEmpty()){
                    passwordText.setError("Password cannot be empty");
                } else if (email.isEmpty()) {
                    usernameText.setError("Email cannot be empty");
                } else if (Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    usernameText.setError("Please enter a valid email");
                } else {
                    loginUser(email, pass);
                }
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(HomeFragment.this)
                        .navigate(R.id.action_nav_home_to_signUp);
            }
        });


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

    /*

    private void setLoginInfo() {
        password = passwordText.getText().toString();
        username = usernameText.getText().toString();
    }

     */

    private void loginUser(String email, String pass) {
        auth.signInWithEmailAndPassword(email, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                //Toast.makeText(HomeFragment.this, "Login Successful", Toast.LENGTH_SHORT).show();
                System.out.println("Login Successful");
                NavHostFragment.findNavController(HomeFragment.this)
                        .navigate(R.id.action_nav_home_to_nav_slideshow);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //Toast.makeText(HomeFragment.this, "Login Failed", Toast.LENGTH_SHORT).show();
                System.out.println("Login Failed");
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}