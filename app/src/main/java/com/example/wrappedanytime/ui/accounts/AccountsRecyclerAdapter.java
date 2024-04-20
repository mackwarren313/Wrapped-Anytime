package com.example.wrappedanytime.ui.accounts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wrappedanytime.R;
import com.example.wrappedanytime.firebasewrapper.FirestoreDatabase;
import com.example.wrappedanytime.spotify.Datatypes.UserData;
import com.example.wrappedanytime.ui.home.HomeFragment;
import com.example.wrappedanytime.ui.slideshow.RecyclerAdapter;
import com.example.wrappedanytime.ui.slideshow.SlideshowFragment;

import java.util.ArrayList;

public class AccountsRecyclerAdapter extends RecyclerView.Adapter<AccountsRecyclerAdapter.MyViewHolder>{

    Context context;
    private ArrayList<UserData> list;


    public AccountsRecyclerAdapter(Context context, ArrayList<UserData> list){
        this.list = list;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView text;
        private Button deleteButton;
        private Button loadButton;
        UserData user;
        public MyViewHolder(final View view) {
            super(view);
            text = view.findViewById(R.id.account_name);
            deleteButton = view.findViewById(R.id.delete);
            loadButton = view.findViewById(R.id.load);
        }
    }

    @NonNull
    @Override
    public AccountsRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull AccountsRecyclerAdapter.MyViewHolder holder, int position) {
        UserData userData = list.get(position);
        holder.text.setText(list.get(position).toString());
        holder.user = userData;

        holder.loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SlideshowFragment.data = holder.user;

            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountsFragment.accounts.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
