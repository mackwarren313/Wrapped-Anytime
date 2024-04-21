package com.example.wrappedanytime.ui.accounts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wrappedanytime.R;
import com.example.wrappedanytime.databinding.FragmentAccountsBinding;
import com.example.wrappedanytime.spotify.Datatypes.UserData;

import java.util.ArrayList;


public class AccountsFragment extends Fragment {

    private FragmentAccountsBinding binding;
    RecyclerView accountsListView;
    public static ArrayList<UserData> accounts;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AccountsViewModel accountsViewModel =
                new ViewModelProvider(this).get(AccountsViewModel.class);

        binding = FragmentAccountsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        loadFromDB();

        //the change to the db happens in the buttons in the AccountsRecyclerAdaptor
        accountsListView = root.findViewById(R.id.top_artists_list);
        accountsListView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        accountsListView.setAdapter(new AccountsRecyclerAdapter(getContext().getApplicationContext(), accounts));

        return root;
    }

    public static void loadFromDB(){
        //for (int i = 0; i < FirestoreDatabase.USER_COLLECTION.)
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}