package com.example.wrappedanytime.ui.previousWrappeds;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.wrappedanytime.R;
import com.example.wrappedanytime.databinding.FragmentPreviousWrappedsBinding;
import com.example.wrappedanytime.databinding.FragmentSlideshowBinding;
import com.example.wrappedanytime.spotify.Audio;
import com.example.wrappedanytime.ui.home.SignUpViewModel;

public class PreviousWrappedsFragment extends Fragment {
    private FragmentPreviousWrappedsBinding binding;
    private Button generateNew;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        PreviousWrappedsViewModel previousWrappedsViewModel =
                new ViewModelProvider(this).get(PreviousWrappedsViewModel.class);
        binding = FragmentPreviousWrappedsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        generateNew = root.findViewById(R.id.genNewWrapped);
        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
