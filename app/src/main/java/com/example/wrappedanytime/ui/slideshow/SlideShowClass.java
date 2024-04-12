package com.example.wrappedanytime.ui.slideshow;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.example.wrappedanytime.ui.home.SignUpFragment;

public class SlideShowClass extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, new SlideshowFragment()).commit();}
    }
}
