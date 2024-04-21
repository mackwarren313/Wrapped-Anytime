package com.example.wrappedanytime.ui.previousWrappeds;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;


public class PreviousWrappedsClass extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, new PreviousWrappedsFragment()).commit();}
    }
}
