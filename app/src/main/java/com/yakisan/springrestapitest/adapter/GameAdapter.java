package com.yakisan.springrestapitest.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.yakisan.springrestapitest.R;
import com.yakisan.springrestapitest.model.Game;
import com.yakisan.springrestapitest.service.API;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.VH> {
    Context context;
    ArrayList<Game> list;

    static ClickListener clickListener;

    public GameAdapter(Context context, ArrayList<Game> list) {
        this.context = context;
        this.list = list;
    }

    public GameAdapter(Context context, ArrayList<Game> list, ClickListener clickListener) {
        this.context = context;
        this.list = list;
        GameAdapter.clickListener = clickListener;
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
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    //ViewHolder
    public static class VH extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView name, description, genre;
        ImageView imgBig;
        CardView card;

        public VH(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.materialCardView);
            name = itemView.findViewById(R.id.tvGameName);
            description = itemView.findViewById(R.id.tvShortDescription);
            genre = itemView.findViewById(R.id.tvGenre);
            imgBig = itemView.findViewById(R.id.gameThumbnailBig);

            //click
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int positionID = getAdapterPosition();
            view = card;
            if (positionID >= 0) {
                clickListener.onItemClick(view, positionID);
            }
        }

        @Override
        public boolean onLongClick(View view) {
            int position = getAdapterPosition();
            view = card;
            if (position >= 0) {
                clickListener.onItemLongClick(view, position);
                return true;
            }
            return false;
        }
    }


    //Interface
    public interface ClickListener {
        void onItemClick(View v, int position);

        void onItemLongClick(View v, int position);
    }


}
