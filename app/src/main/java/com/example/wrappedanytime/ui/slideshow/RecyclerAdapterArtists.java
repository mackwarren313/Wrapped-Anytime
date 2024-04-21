package com.example.wrappedanytime.ui.slideshow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wrappedanytime.R;
import com.example.wrappedanytime.spotify.Datatypes.Artist;

import java.util.List;

public class RecyclerAdapterArtists extends RecyclerView.Adapter<RecyclerAdapterArtists.MyViewHolder>{

    Context context;
    private List<Artist> list;


    public RecyclerAdapterArtists(Context context, List<Artist> list){
        this.list = list;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView text;
        private ImageView image;
        public MyViewHolder(final View view) {
            super(view);
            text = view.findViewById(R.id.list_item_text);
            image = view.findViewById(R.id.image);
        }
    }

    @NonNull
    @Override
    public RecyclerAdapterArtists.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterArtists.MyViewHolder holder, int position) {
        holder.text.setText(list.get(position).getName());

        list.get(position).getImage().setImageView(holder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
