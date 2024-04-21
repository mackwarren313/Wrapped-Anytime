package com.example.wrappedanytime.ui.slideshow;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wrappedanytime.R;
import com.example.wrappedanytime.spotify.Datatypes.Album;
import com.example.wrappedanytime.spotify.Datatypes.Track;
import com.example.wrappedanytime.spotify.SpotifyData;
import com.example.wrappedanytime.ui.home.HomeFragment;

import java.util.List;

public class RecyclerAdapterTracks extends RecyclerView.Adapter<RecyclerAdapterTracks.MyViewHolder>{

    Activity context;
    private List<Track> list;



    public RecyclerAdapterTracks(Activity context, List<Track> list){
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
    public RecyclerAdapterTracks.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterTracks.MyViewHolder holder, int position) {
        holder.text.setText(list.get(position).getName());

        SpotifyData dataRetriever = new SpotifyData(context);

        String topTrackAlbum = list.get(position).getAlbumID();
        Log.d("topAlbum", topTrackAlbum);
        Album topAlbum = dataRetriever.getAlbum(topTrackAlbum);
        topAlbum.getCoverArt().setImageView(holder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
