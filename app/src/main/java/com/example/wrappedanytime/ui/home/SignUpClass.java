package com.example.wrappedanytime.ui.home;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
public class SignUpClass extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, new SignUpFragment()).commit();}
    }
}

