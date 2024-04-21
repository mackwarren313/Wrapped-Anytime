package com.example.wrappedanytime.ui.previousWrappeds;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.wrappedanytime.R;
import com.example.wrappedanytime.databinding.FragmentPreviousWrappedsBinding;
import com.example.wrappedanytime.databinding.FragmentSlideshowBinding;
import com.example.wrappedanytime.spotify.Audio;

public class PreviousWrappedsFragment extends Fragment {
    private FragmentPreviousWrappedsBinding binding;
    private Button generateNew;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
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
