package com.example.wrappedanytime.ui.slideshow;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.example.wrappedanytime.spotify.Datatypes.UserData;
import com.example.wrappedanytime.ui.home.SignUpFragment;
import com.google.gson.Gson;

public class SlideShowClass extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserData ud = new Gson().fromJson(getIntent().getStringExtra("myjson"), UserData.class);
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, new SlideshowFragment(ud)).commit();}
    }
}
