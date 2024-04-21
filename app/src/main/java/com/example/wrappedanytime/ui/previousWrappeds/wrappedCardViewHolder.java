package com.example.wrappedanytime.ui.previousWrappeds;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wrappedanytime.R;

public class wrappedCardViewHolder extends RecyclerView.ViewHolder {
    View view;
    TextView genDate;
    TextView timeRange;
    TextView genre;
    ImageView topArtist;
    public wrappedCardViewHolder(@NonNull View itemView) {
        super(itemView);
        genDate = itemView.findViewById(R.id.card_date);
        timeRange = itemView.findViewById(R.id.card_timeRange);
        genre = itemView.findViewById(R.id.card_topGenre);
        topArtist = itemView.findViewById(R.id.card_image);
        view = itemView;
    }
}
