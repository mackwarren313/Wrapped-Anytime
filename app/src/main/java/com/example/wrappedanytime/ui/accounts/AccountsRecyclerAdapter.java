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
import com.example.wrappedanytime.spotify.Datatypes.UserData;

import java.util.ArrayList;

public class AccountsRecyclerAdapter extends RecyclerView.Adapter<AccountsRecyclerAdapter.MyViewHolder>{

    Context context;
    private ArrayList<UserData> list;


    public AccountsRecyclerAdapter(Context context, ArrayList<UserData> list){
        this.list = list;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView accountName;
        private Button deleteButton;
        private Button loadButton;
        UserData user;
        public MyViewHolder(final View view) {
            super(view);
            accountName = view.findViewById(R.id.account_name);
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
        holder.accountName.setText(list.get(position).toString());
        holder.user = userData;

        //I cant remember if we are storing a userdata or spotify data but i think its user data
        //if userdata keep it as is, if its spotify data tell me and i can update the list and small things quickly

        holder.loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get email and password from data stored here
                // HomeFragment homeFragment = new HomeFragment();
                //homeFragment.loginUser();
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountsFragment.accounts.remove(holder.getAdapterPosition());
                notifyDataSetChanged();

                //FirestoreDatabase.deleteUserData(holder.user);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
