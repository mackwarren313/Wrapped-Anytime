package com.example.wrappedanytime.ui.home;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.wrappedanytime.R;
import com.example.wrappedanytime.databinding.FragmentEditAccountBinding;
import com.example.wrappedanytime.databinding.FragmentSignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EditAccountFragment extends Fragment {

    //private SignUpViewModel mViewModel;

    private FragmentEditAccountBinding binding;
    private MediaPlayer mp;
    private String username;
    private String password;
    EditText usernameText;
    EditText passwordText;

    EditText changeEmail;
    EditText changePass;

    private FirebaseAuth auth;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentEditAccountBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        auth = FirebaseAuth.getInstance();

        usernameText = root.findViewById(R.id.Username);
        passwordText = root.findViewById(R.id.Password);
        changeEmail = root.findViewById(R.id.changeEmail);
        changePass = root.findViewById(R.id.changePassword);
        Bundle b = this.getActivity().getIntent().getExtras();
        String oldUser = b.getString("user");
        String oldPass = b.getString("pass");
        Button update = root.findViewById(R.id.update_button);
        Button delete = root.findViewById(R.id.delete_button);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = changeEmail.getText().toString().trim();
                String pass = changePass.getText().toString().trim();

                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    if (!pass.isEmpty()) {
                        change(email, pass, oldUser, oldPass);
                    } else {
                        changePass.setError("Password cannot be empty");
                    }
                } else if (email.isEmpty()) {
                    changeEmail.setError("Email cannot be empty");
                } else {
                    changeEmail.setError("Please enter a valid email");
                }

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                user.delete();
                NavHostFragment.findNavController(EditAccountFragment.this)
                        .navigate(R.id.action_edit_to_homeFragment);
            }
        });

        return root;
    }



    // Here we are going to change our email using firebase re-authentication
    private void change(String email, final String password, String oldEmail, String oldPassword) {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        // Get auth credentials from the user for re-authentication
        AuthCredential credential = EmailAuthProvider.getCredential(oldEmail, oldPassword); // Current Login Credentials

        // Prompt the user to re-provide their sign-in credentials
        user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                Log.d("value", "User re-authenticated.");

                // Now change your email address \\
                //----------------Code for Changing Email Address----------\\
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                user.updateEmail(changeEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            System.out.println("Successful email update");
                        }
                    }
                });
            }
        });
        user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                Log.d("value", "User re-authenticated.");
                user.updatePassword(changePass.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            System.out.println("Successful pass update");
                            NavHostFragment.findNavController(EditAccountFragment.this)
                                    .navigate(R.id.action_edit_to_homeFragment);
                        }
                    }
                });
            }
        });
}

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}