package com.yakisan.springrestapitest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.yakisan.springrestapitest.R;
import com.yakisan.springrestapitest.model.Game;

import java.util.ArrayList;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.VH> {
    Context context;
    ArrayList<Game> list;

    public GameAdapter(Context context, ArrayList<Game> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.game_item, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Game game = list.get(position);
        holder.name.setText(game.getName());
        holder.description.setText(game.getDescription());
        holder.genre.setText(game.getGenre());

        //Glide
        String imageUrlBig = game.getImageUrl();
        Glide.with(context)
                .load(imageUrlBig)
                .into(holder.imgBig);

        String imageUrlSmall = game.getImageUrl();
        Glide.with(context)
                .load(imageUrlSmall)
                .into(holder.imgSmall);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //ViewHolder
    public static class VH extends RecyclerView.ViewHolder{
        TextView name, description, genre;
        ImageView imgBig, imgSmall;
        public VH(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvGameName);
            description = itemView.findViewById(R.id.tvShortDescription);
            genre = itemView.findViewById(R.id.tvGenre);
            imgBig = itemView.findViewById(R.id.gameThumbnailBig);
            imgSmall = itemView.findViewById(R.id.gameThumbnailSmall);
        }
    }
}