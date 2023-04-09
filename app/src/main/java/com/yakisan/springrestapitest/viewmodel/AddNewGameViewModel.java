package com.yakisan.springrestapitest.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.yakisan.springrestapitest.databinding.ActivityAddNewGameBinding;
import com.yakisan.springrestapitest.model.Game;
import com.yakisan.springrestapitest.service.API;
import com.yakisan.springrestapitest.view.AddNewGame;
import com.yakisan.springrestapitest.view.HomePage;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewGameViewModel extends ViewModel {
    //Post game
    public void isPostNewGame(ActivityAddNewGameBinding binding, Context context) {
        //TextField bilesenlerinin iceriginin alinmasi
        String gameName = binding.textFieldOyunIsmi.getText().toString();
        String gameDesc = binding.textFieldOyunAciklamasi.getText().toString();
        String gameGenre = binding.textFieldOyunTuru.getText().toString();
        String imageUrl = binding.textFieldOyunImageUrl.getText().toString();

        if (TextUtils.isEmpty(gameName) || TextUtils.isEmpty(gameDesc) || TextUtils.isEmpty(gameGenre) || TextUtils.isEmpty(imageUrl)) {
            Toast.makeText(context, "Boş geçilemez", Toast.LENGTH_SHORT).show();
        } else {
            //Veritabanına POST isleminin yapilmasi
            Call<ArrayList<Game>> call = API.getRetrofitClient().createNewGame(new Game(gameName, gameDesc, gameGenre, imageUrl));
            call.enqueue(new Callback<ArrayList<Game>>() {
                @Override
                public void onResponse(@NonNull Call<ArrayList<Game>> call, @NonNull Response<ArrayList<Game>> response) {
                    if (response.isSuccessful()) {
                        assert response.body() != null;
                        Log.e("Başarılı: ", response.body().toString());
                        Toast.makeText(context, "Eklenen oyun: " + response.body().get(response.body().size() - 1).getName(), Toast.LENGTH_SHORT).show();
                        //Diger sayfaya yonlendirme
                        Intent intent = new Intent(context, HomePage.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ArrayList<Game>> call, @NonNull Throwable t) {
                    Log.e("Response Hatası: ", t.getMessage());
                }
            });
        }
    }

    //Yeni oyunun kaydedilmesi
    public void saveAndPostNewGame(ActivityAddNewGameBinding binding, Context context) {
        binding.containedButton.setOnClickListener(v -> {
            //Oyunu kaydet
            isPostNewGame(binding, context);
        });
    }


}