package com.yakisan.springrestapitest.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.yakisan.springrestapitest.adapter.GameAdapter;
import com.yakisan.springrestapitest.databinding.ActivityHomepageBinding;
import com.yakisan.springrestapitest.model.Game;
import com.yakisan.springrestapitest.service.API;
import com.yakisan.springrestapitest.viewmodel.HomePageViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePage extends AppCompatActivity {
    ArrayList<Game> games = new ArrayList<>();
    GameAdapter adapter;
    ActivityHomepageBinding binding;
    HomePageViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomepageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Viewmodel Binding
        viewModel = ViewModelProviders.of(this).get(HomePageViewModel.class);
        //RV initialize
        initialize();
        //oyunları getirir.
        viewModel.getAllGames(binding,this, games, adapter);
        //fab islevini yerine getirir.
        viewModel.fabClicked(binding, this);



    }

    @Override
    protected void onStart() {
        super.onStart();
        //Tum sayfayi yeniler.
        viewModel.refresh(binding,this, games, adapter);
    }

    //initialize
    public void initialize() {
        StaggeredGridLayoutManager sgm = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        binding.rvGames.setLayoutManager(sgm);
        games = new ArrayList<>();
        adapter = new GameAdapter(getApplicationContext(), games, new GameAdapter.ClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Game model = games.get(position);
                Log.e("Clicked: ", model.getName());
                viewModel.assign(position, games, getApplicationContext());
            }

            @Override
            public void onItemLongClick(View v, int position) {
                Game model = games.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(HomePage.this);
                builder.setTitle("Emin misiniz?");
                builder.setMessage(model.getName() + " isimli oyunu silmek istediğinizden emin misiniz?");
                builder.setNegativeButton("Hayır", null);
                builder.setPositiveButton("Evet", (dialogInterface, i) -> {
                    Log.e("Silinen oyun: ", model.getName());
                    viewModel.deleteGameByID(binding, getApplicationContext(), adapter, model.getId(), games);
                    adapter.notifyDataSetChanged();
                });
                builder.show();
            }
        });
        binding.rvGames.setAdapter(adapter);
    }


}