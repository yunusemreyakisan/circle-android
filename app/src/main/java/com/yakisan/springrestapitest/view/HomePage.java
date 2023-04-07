package com.yakisan.springrestapitest.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
        adapter = new GameAdapter(this, games);
        binding.rvGames.setAdapter(adapter);
    }


}