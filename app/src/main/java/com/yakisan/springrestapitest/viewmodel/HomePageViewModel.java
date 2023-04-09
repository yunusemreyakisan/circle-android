package com.yakisan.springrestapitest.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.yakisan.springrestapitest.adapter.GameAdapter;
import com.yakisan.springrestapitest.databinding.ActivityHomepageBinding;
import com.yakisan.springrestapitest.model.Game;
import com.yakisan.springrestapitest.service.API;
import com.yakisan.springrestapitest.view.AddNewGame;
import com.yakisan.springrestapitest.view.UpdateGame;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePageViewModel extends ViewModel {
    //BASE_URL -> http://10.0.2.2:8080/

    //Tum sayfayi yeniler.
    public void refresh(ActivityHomepageBinding binding, Context context, ArrayList<Game> games, GameAdapter adapter){
        binding.swipeRefresh.setOnRefreshListener(() -> {
            games.clear();
            getAllGames(binding, context, games, adapter);
            binding.swipeRefresh.setRefreshing(false);
        });
    }


    //Secilen oyunu siler.
    public void deleteGameByID(ActivityHomepageBinding binding, Context context, GameAdapter adapter, int pos, ArrayList<Game> games){
       Call<String> call = API.getRetrofitClient().deleteGame(pos);
       call.enqueue(new Callback<String>() {
           @Override
           public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
               assert response.body() != null;
               Log.e("Silinen oyun : ", response.body());
           }

           @Override
           public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
               games.clear();
               getAllGames(binding, context, games,adapter);
               Log.e("Oyun silerken hata: ", t.getMessage());
           }
       });
    }

    //Tüm oyunlari getirir.
    public void getAllGames(ActivityHomepageBinding binding, Context context, ArrayList<Game> games, GameAdapter adapter) {
        binding.progressBar.setVisibility(View.VISIBLE);
        API.getRetrofitClient().getAllGames().enqueue(new Callback<ArrayList<Game>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<Game>> call, @NonNull Response<ArrayList<Game>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    binding.progressBar.setVisibility(View.GONE);
                    games.addAll(response.body());
                    adapter.notifyDataSetChanged();
                    Log.e("oyun listesi", games.toString());
                } else {
                    binding.progressBar.setVisibility(View.GONE);
                    Toast.makeText(context, "API Hatası", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Game>> call, @NonNull Throwable t) {
                Toast.makeText(context, "Hatalı", Toast.LENGTH_SHORT).show();
                Log.e("Oyun listesi yuklenirken hata", t.getMessage());
            }
        });
    }



    //FAB islevi
    public void fabClicked(ActivityHomepageBinding binding, Context context){
        binding.extendedFab.setOnClickListener(v -> {
            Intent intent = new Intent(context, AddNewGame.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }


    //Intent game properties -> Game
    public void assign(int position, ArrayList<Game> games, Context context){
        Game model = games.get(position);
        //Intent game properties -> Game
        Intent intent = new Intent(context, UpdateGame.class);
        intent.putExtra("id", model.getId());
        intent.putExtra("name", model.getName());
        intent.putExtra("desc", model.getDescription());
        intent.putExtra("genre", model.getGenre());
        intent.putExtra("url", model.getImageUrl());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
