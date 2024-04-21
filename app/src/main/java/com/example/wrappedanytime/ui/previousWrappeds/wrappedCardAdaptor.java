package com.example.wrappedanytime.ui.previousWrappeds;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wrappedanytime.R;
import com.example.wrappedanytime.spotify.Datatypes.User;
import com.example.wrappedanytime.spotify.Datatypes.UserData;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class wrappedCardAdaptor extends RecyclerView.Adapter<wrappedCardViewHolder> {
    List<UserData> list
            = Collections.emptyList();
    private OnClickListener onClickListener;
    Context context;
    public wrappedCardAdaptor(List<UserData> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public wrappedCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context
                = parent.getContext();
        LayoutInflater inflater
                = LayoutInflater.from(context);

        // Inflate the layout

        View layoutView
                = inflater
                .inflate(R.layout.wrapped_card,
                        parent, false);

        wrappedCardViewHolder viewHolder
                = new wrappedCardViewHolder(layoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull wrappedCardViewHolder holder, int position) {
        UserData current = list.get(position);
        Date genDate = current.getGenDate();
        StringBuilder sb = new StringBuilder();
        sb.append(genDate.getMonth() + 1);
        sb.append("/");
        sb.append(genDate.getDate());
        sb.append("/");
        sb.append(genDate.getYear()-100);
        holder.genDate.setText("Generated on " + sb);
        String range;
        switch(current.getTr()) {
            case LONG:
                range = "year";
                break;
            case MEDIUM:
                range = "6 months";
                break;
            case SHORT:
                range = "month";
                break;
            default:
                range = "month";
        }
        holder.timeRange.setText("From the last " + range + "!");
        holder.genre.setText("Top Genre: " + current.getTopGenre());
        list.get(position).getTopArtists().get(0).getImage().setImageView(holder.topArtist);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.onClick(position, current);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
    @Override
    public void onAttachedToRecyclerView(
            RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }
    public interface OnClickListener {
        void onClick(int position, UserData model);
    }
}
