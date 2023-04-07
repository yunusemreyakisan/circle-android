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
import com.yakisan.springrestapitest.databinding.ActivityAddNewGameBinding;
import com.yakisan.springrestapitest.model.Game;
import com.yakisan.springrestapitest.service.API;
import com.yakisan.springrestapitest.view.AddNewGame;
import com.yakisan.springrestapitest.view.HomePage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewGameViewModel extends ViewModel {
    boolean durum = false;
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
            Call<Game> call = API.getRetrofitClient().createNewGame(new Game(gameName, gameDesc, gameGenre, imageUrl));
            call.enqueue(new Callback<Game>() {
                @Override
                public void onResponse(@NonNull Call<Game> call, @NonNull Response<Game> response) {
                    Log.e("Başarılı: ", response.body().toString());

                    durum = true;

                }

                @Override
                public void onFailure(@NonNull Call<Game> call, @NonNull Throwable t) {
                    Log.e("Response Hatası: ", t.getMessage());
                    durum = false;
                }
            });
        }
    }

    //TODO: E/ResponseHatası:: java.lang.IllegalStateException: Expected BEGIN_OBJECT but was BEGIN_ARRAY at line 1 column 2 path $

    //Yeni oyunun kaydedilmesi
    public void saveAndPostNewGame(ActivityAddNewGameBinding binding, Context context) {
        binding.containedButton.setOnClickListener(v -> {
            //Oyunu kaydet
            isPostNewGame(binding, context);
            if(durum){
                Intent intent = new Intent(context, HomePage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }

        });
    }




}
