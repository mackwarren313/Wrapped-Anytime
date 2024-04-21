package com.example.wrappedanytime.ui.previousWrappeds;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.TextViewCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wrappedanytime.MainActivity;
import com.example.wrappedanytime.R;
import com.example.wrappedanytime.databinding.FragmentPreviousWrappedsBinding;
import com.example.wrappedanytime.databinding.FragmentSlideshowBinding;
import com.example.wrappedanytime.spotify.Audio;
import com.example.wrappedanytime.spotify.Datatypes.User;
import com.example.wrappedanytime.spotify.Datatypes.UserData;
import com.example.wrappedanytime.spotify.SpotifyData;
import com.example.wrappedanytime.ui.home.HomeFragment;
import com.example.wrappedanytime.ui.home.SignUpViewModel;
import com.example.wrappedanytime.ui.slideshow.SlideShowClass;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class PreviousWrappedsFragment extends Fragment {
    private FragmentPreviousWrappedsBinding binding;
    private Button generateNew;
    private TextView title;
    private RecyclerView recyclerView;
    private wrappedCardAdaptor adaptor;
    private List<UserData> wrappedList;
    private SpotifyData dataRetriever = new SpotifyData(this.getActivity());
    private User user = dataRetriever.getUser();
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        PreviousWrappedsViewModel previousWrappedsViewModel =
                new ViewModelProvider(this).get(PreviousWrappedsViewModel.class);
        binding = FragmentPreviousWrappedsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        generateNew = root.findViewById(R.id.genNewWrapped);
        title = root.findViewById(R.id.WrappedTitle);
        title.setText(user.getDisplayName() + "'s Wrappeds");
        wrappedList = new ArrayList<>();
        recyclerView = root.findViewById(R.id.oldWrapRecycler);
        adaptor = new wrappedCardAdaptor(wrappedList, getActivity().getApplication());
        recyclerView.setAdapter(adaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adaptor.setOnClickListener(new wrappedCardAdaptor.OnClickListener() {
            @Override
            public void onClick(int position, UserData model) {
                Intent wrappedIntent = new Intent(getContext(), SlideShowClass.class);
                Gson gson = new Gson();
                String wrappedJson = gson.toJson(model);
                wrappedIntent.putExtra("myjson", wrappedJson);
                startActivity(wrappedIntent);
            }
        });
        generateNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(v.getContext(), generateNew);

                // Inflating popup menu from popup_menu.xml file
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        UserData.TimeRange tr;
                        CharSequence menuItemTitle = menuItem.getTitle();
                        if (menuItemTitle.equals("1 Month")) {
                            tr = UserData.TimeRange.SHORT;
                        } else if (menuItemTitle.equals("6 Months")) {
                            tr = UserData.TimeRange.MEDIUM;
                        } else if (menuItemTitle.equals("1 Year")) {
                            tr = UserData.TimeRange.LONG;
                        } else {
                            tr = UserData.TimeRange.SHORT;
                        }
                        UserData newWrapped = dataRetriever.getUserData(tr);
                        wrappedList.add(newWrapped);
                        adaptor.notifyDataSetChanged();
                        Intent wrappedIntent = new Intent(v.getContext(), SlideShowClass.class);
                        Gson gson = new Gson();
                        String wrappedJson = gson.toJson(newWrapped);
                        wrappedIntent.putExtra("myjson", wrappedJson);
                        startActivity(wrappedIntent);
                        return true;
                    }
                });
                // Showing the popup menu
                popupMenu.show();
            }
        });

        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
