package com.example.wrappedanytime;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.example.wrappedanytime.firebasewrapper.FirestoreDatabase;
import com.example.wrappedanytime.firebasewrapper.data.UserData;
import com.example.wrappedanytime.spotify.Authentication;
import com.example.wrappedanytime.spotify.Datatypes.User;
import com.example.wrappedanytime.spotify.SpotifyData;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wrappedanytime.databinding.ActivityMainBinding;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private SpotifyData dataRetriever;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Authentication.setToken("BQBzNZ8lJm0aHicLDA5P6RSVlVlXug1AP9E9gSu--kMP9x1Ants-TLgNpHfgTdoEOvNYuBwNGJp1Zw7nRJT9XoK4evBwVt36G5YjNfWwXMWW7fsjgSguscV_gyu1El8Tw6-yzu3tkN2dRres6QXO64xoQO0cXjmKKOqWn6RUKMR7j3QTqD4P6z6w2dktxP5qsnpAUkWSfA-iKg");
        String token = "BQBzNZ8lJm0aHicLDA5P6RSVlVlXug1AP9E9gSu--kMP9x1Ants-TLgNpHfgTdoEOvNYuBwNGJp1Zw7nRJT9XoK4evBwVt36G5YjNfWwXMWW7fsjgSguscV_gyu1El8Tw6-yzu3tkN2dRres6QXO64xoQO0cXjmKKOqWn6RUKMR7j3QTqD4P6z6w2dktxP5qsnpAUkWSfA-iKg";
        //Above token should be gotten via firebase, this is my perosnal token, don't leak it lmao
        if (!Authentication.testAuth(token, this)) {
            Authentication.getToken(MainActivity.this);
        } else {
            Authentication.setToken(token);
            afterAuthWork();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Authentication.storeAuth(requestCode, resultCode, data);
        afterAuthWork();
    }

    private void afterAuthWork() {
        Log.d("myLog", Authentication.getAccessToken());
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dataRetriever = new SpotifyData(MainActivity.this);
        setSupportActionBar(binding.appBarMain.toolbar);
        User user = dataRetriever.getUser();
        Log.d("userDataLog", user.toString());
        /**binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("myLog", "before user call");
                User user = dataRetriever.getUser();
                Log.d("myLog", user.toString());
                Log.d("myLog", "after user call");

            }
        });**/ // believe this is just the email icon
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
