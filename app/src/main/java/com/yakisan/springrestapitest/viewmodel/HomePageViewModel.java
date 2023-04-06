package com.yakisan.springrestapitest.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.yakisan.springrestapitest.adapter.GameAdapter;
import com.yakisan.springrestapitest.databinding.ActivityHomepageBinding;
import com.yakisan.springrestapitest.model.Game;
import com.yakisan.springrestapitest.service.API;
import com.yakisan.springrestapitest.view.HomePage;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePageViewModel extends ViewModel {
    //BASE_URL -> http://10.0.2.2:8080/



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
                    Log.e("oyun 1: ", games.get(0).getImageUrl());
                    Log.e("oyun 1: ", games.get(0).getName());
                } else {
                    binding.progressBar.setVisibility(View.GONE);
                    Toast.makeText(context, "API Hatası", Toast.LENGTH_SHORT).show();
                    Log.e("empty_body", "Body Boş geliyor.");
                }

            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Game>> call, @NonNull Throwable t) {
                Toast.makeText(context, "Hatalı", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
