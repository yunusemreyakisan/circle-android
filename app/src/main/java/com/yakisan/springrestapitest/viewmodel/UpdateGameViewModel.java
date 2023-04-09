package com.yakisan.springrestapitest.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.yakisan.springrestapitest.databinding.ActivityUpdateGameBinding;
import com.yakisan.springrestapitest.model.Game;
import com.yakisan.springrestapitest.service.API;
import com.yakisan.springrestapitest.view.HomePage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateGameViewModel extends ViewModel {

    //Update Game
    public void update(ActivityUpdateGameBinding binding, int id, Context context){
        //Verilerin yeni degerlerinin alinmasi
        String gName = binding.textFieldOyunIsmiUpdate.getText().toString();
        String gDesc = binding.textFieldOyunAciklamasiUpdate.getText().toString();
        String gGenre = binding.textFieldOyunTuruUpdate.getText().toString();
        String gUrl = binding.textFieldOyunImageUrlUpdate.getText().toString();

        //Update
        Call<Game> call = API.getRetrofitClient().updateGame(id, new Game(id, gName, gDesc, gGenre, gUrl));
        call.enqueue(new Callback<Game>() {
            @Override
            public void onResponse(@NonNull Call<Game> call, @NonNull Response<Game> response) {
                assert response.body() != null;
                Log.e("Guncellenen oyun: ", response.body().getName());
                Toast.makeText(context, response.body().getId()+ " id'li oyun güncellendi.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NonNull Call<Game> call, @NonNull Throwable t) {
                Log.e("Oyun guncellemede hata : ", t.getMessage());
            }
        });
    }

    //Verify update
    public void verify(ActivityUpdateGameBinding binding, Context context, int id){
        binding.btnUpdateGame.setOnClickListener(v -> {
            //Oyun guncellemesi
            update(binding, id, context);
            Intent intent = new Intent(context, HomePage.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

}
