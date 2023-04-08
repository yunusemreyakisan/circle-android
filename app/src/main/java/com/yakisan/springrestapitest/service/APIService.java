package com.yakisan.springrestapitest.service;

import com.yakisan.springrestapitest.model.Game;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {
    //BASE URL ->  http://10.0.2.2:8080/
    @GET("/games")
    Call<ArrayList<Game>> getAllGames();

    @POST("/add")
    Call<ArrayList<Game>> createNewGame(@Body Game game);

    //TODO: PUT, DELETE, POST işlemlerini ekle.

}
