package com.yakisan.springrestapitest.service;

import com.yakisan.springrestapitest.model.Game;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIService {
    //Emulator BASE URL ->  http://10.0.2.2:8080/
    @GET("/games")
    Call<ArrayList<Game>> getAllGames();

    @POST("/add")
    Call<ArrayList<Game>> createNewGame(@Body Game game);

    @DELETE("/delete/{id}")
    Call<String> deleteGame(@Path("id") int gameId);

    @PUT("/update/{id}")
    Call<Game> updateGame(@Path("id") int gameId, @Body Game game);


}
