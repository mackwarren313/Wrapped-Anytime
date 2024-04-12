package com.example.wrappedanytime.ui.home;

import androidx.lifecycle.ViewModelProvider;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.wrappedanytime.R;
import com.example.wrappedanytime.databinding.FragmentSignUpBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpFragment extends Fragment {

    //private SignUpViewModel mViewModel;

    private FragmentSignUpBinding binding;
    private MediaPlayer mp;
    private String username;
    private String password;
    EditText usernameText;
    EditText passwordText;

    private FirebaseAuth auth;

    //public static SignUp newInstance() {return new SignUp();}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        SignUpViewModel signUpViewModel =
                new ViewModelProvider(this).get(SignUpViewModel.class);

        binding = FragmentSignUpBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        auth = FirebaseAuth.getInstance();

        usernameText = root.findViewById(R.id.Username);
        passwordText = root.findViewById(R.id.Password);

        Button login = root.findViewById(R.id.login_page_button);
        Button register = root.findViewById(R.id.register_button);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = usernameText.getText().toString().trim();
                String pass = passwordText.getText().toString().trim();

                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    if (!pass.isEmpty()) {
                        registerUser(email,pass);
                    } else {
                        passwordText.setError("Password cannot be empty");
                    }
                } else if (email.isEmpty()) {
                    usernameText.setError("Email cannot be empty");
                } else {
                    usernameText.setError("Please enter a valid email");
                }

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(SignUpFragment.this)
                        .navigate(R.id.action_signUp_to_homeFragment);
            }
        });

        return root;
    }

    private void registerUser(String email, String pass) {
        auth.createUserWithEmailAndPassword(email, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                //Toast.makeText(HomeFragment.this, "Login Successful", Toast.LENGTH_SHORT).show();
                System.out.println("Register Successful");
                NavHostFragment.findNavController(SignUpFragment.this)
                        .navigate(R.id.action_signUp_to_homeFragment);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //Toast.makeText(HomeFragment.this, "Login Failed", Toast.LENGTH_SHORT).show();
                System.out.println("Register Failed");
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}