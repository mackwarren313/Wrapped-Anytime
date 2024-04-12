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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.wrappedanytime.MainActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.wrappedanytime.MainActivity;
import com.example.wrappedanytime.R;
import com.example.wrappedanytime.databinding.ActivityMainBinding;
import com.example.wrappedanytime.databinding.FragmentHomeBinding;
import com.example.wrappedanytime.spotify.Audio;
import com.example.wrappedanytime.spotify.Authentication;
import com.example.wrappedanytime.spotify.Datatypes.Album;
import com.example.wrappedanytime.spotify.Datatypes.Artist;
import com.example.wrappedanytime.spotify.Datatypes.Track;
import com.example.wrappedanytime.spotify.Datatypes.User;
import com.example.wrappedanytime.spotify.SpotifyData;
import com.example.wrappedanytime.ui.gallery.GalleryFragment;
import com.example.wrappedanytime.ui.slideshow.SlideShowClass;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.spotify.sdk.android.auth.LoginActivity;


import java.io.IOException;

public class HomeFragment extends AppCompatActivity {

    private FragmentHomeBinding binding;
    private MediaPlayer mp;
    private String username;
    private String password;
    EditText usernameText;
    EditText passwordText;

    private FirebaseAuth auth;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();

        usernameText = findViewById(R.id.Username);
        passwordText = findViewById(R.id.Password);

        Button createAccount = findViewById(R.id.create_account_button);
        Button login = findViewById(R.id.login_button);

        Intent signUpIntent = new Intent(HomeFragment.this, SignUpClass.class);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = usernameText.getText().toString().trim();
                String pass = passwordText.getText().toString().trim();

                if (pass.isEmpty()){
                    passwordText.setError("Password cannot be empty");
                } else if (email.isEmpty()) {
                    usernameText.setError("Email cannot be empty");
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    usernameText.setError("Please enter a valid email");
                } else {
                    loginUser(email, pass);
                }
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(signUpIntent);
            }
        });

    }

    /*
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        //final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

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
                startActivity(signUpIntent);
            }
        });



        Model of how to use the spotify data getter
         * At the top of your class's onCreateView, put:
         * SpotifyData dataRetriever = new SpotifyData(this.getActivity());
         * Then, later, wherever you want the data, put:
         * dataRetriever.getUser();
         * This will return a User object. More objects coming.


        return root;
}
     */




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
                if (Authentication.getAccessToken() == null) {
                    Authentication.getToken(HomeFragment.this);
                } else {
                    afterAuthWork();
                }
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Authentication.storeAuth(requestCode, resultCode, data);
        afterAuthWork();
    }



    public void afterAuthWork() {
        Log.d("myLog", Authentication.getAccessToken());

        //change to main page
        Intent mainIntent = new Intent(HomeFragment.this, SlideShowClass.class);

        startActivity(mainIntent);

        /**binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        Log.d("myLog", "before user call");
        User user = dataRetriever.getUser();
        Log.d("myLog", user.toString());
        Log.d("myLog", "after user call");

        }
        });**/ // believe this is just the email icon
    }

}