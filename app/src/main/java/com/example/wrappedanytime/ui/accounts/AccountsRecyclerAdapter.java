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
import com.example.wrappedanytime.ui.slideshow.RecyclerAdapter;

import java.util.ArrayList;

public class AccountsRecyclerAdapter extends RecyclerView.Adapter<AccountsRecyclerAdapter.MyViewHolder>{

    Context context;
    private ArrayList<String> list;


    public AccountsRecyclerAdapter(Context context, ArrayList<String> list){
        this.list = list;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView accountName;
        private Button deleteButton;
        private Button loadButton;
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
        holder.accountName.setText(list.get(position).toString());

        holder.loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update who the user is from accountName
                //essientially sign in
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //delete from save form accountName
                //remove from db
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
