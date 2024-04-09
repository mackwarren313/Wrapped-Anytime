package com.example.wrappedanytime.ui.Duo;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.wrappedanytime.R;
import com.example.wrappedanytime.databinding.FragmentAccountsBinding;
import com.example.wrappedanytime.databinding.FragmentDuoBinding;
import com.example.wrappedanytime.databinding.FragmentSlideshowBinding;
import com.example.wrappedanytime.ui.slideshow.SlideshowViewModel;

public class DuoFragment extends Fragment {


    private FragmentDuoBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        DuoViewModel duoViewModel =
                new ViewModelProvider(this).get(DuoViewModel.class);
        binding = FragmentDuoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        EditText useOne = root.findViewById(R.id.user_one_et);
        EditText userTwo = root.findViewById(R.id.user_two_et);

        Button makeDuo = root.findViewById(R.id.make_duo_wrapped);
        makeDuo.setOnClickListener(v -> {});

        return root;
    }

    public void makeDuo(){
        //raw create a user data and log that into the wrapped page
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}