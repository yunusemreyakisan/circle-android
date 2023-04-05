package com.yakisan.springrestapitest.viewmodel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.yakisan.springrestapitest.model.User;
import com.yakisan.springrestapitest.service.API;
import com.yakisan.springrestapitest.view.HomePage;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePageViewModel extends ViewModel {
    //BASE_URL -> http://10.0.2.2:8080/
    List<User> users = new ArrayList<>();

    //Tüm kullanicilari getirir.
    public void getAllUsers(Context context) {
        API.getRetrofitClient().getAllUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    users.addAll(response.body());
                    Log.e("user listesi", users.toString());
                } else {
                    Log.e("empty_body", "Body Boş geliyor.");
                }

            }

            @Override
            public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                Toast.makeText(context, "Hatalı", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
