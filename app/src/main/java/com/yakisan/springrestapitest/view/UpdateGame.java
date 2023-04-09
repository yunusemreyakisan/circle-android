package com.yakisan.springrestapitest.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yakisan.springrestapitest.databinding.ActivityUpdateGameBinding;
import com.yakisan.springrestapitest.viewmodel.UpdateGameViewModel;

public class UpdateGame extends AppCompatActivity {

    ActivityUpdateGameBinding binding;
    UpdateGameViewModel viewModel;
    String gameName, gameDesc, genre, url;
    int gameID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //ViewModel Binding
        viewModel = ViewModelProviders.of(this).get(UpdateGameViewModel.class);
        //Verilerin alinmasi
        getIntentData();
        //Verilerin atanmasi
        assignIntentData();
        //Guncellemenin dogrulanmasi
        viewModel.verify(binding, getApplicationContext(), gameID);

    }

    //assign datas
    public void assignIntentData(){
        binding.textFieldOyunIsmiUpdate.setText(gameName);
        binding.textFieldOyunAciklamasiUpdate.setText(gameDesc);
        binding.textFieldOyunTuruUpdate.setText(genre);
        binding.textFieldOyunImageUrlUpdate.setText(url);
    }

    //get intent data
    public void getIntentData(){
        Intent data = getIntent();
        gameID = data.getIntExtra("id", 0);
        gameName = data.getStringExtra("name");
        gameDesc = data.getStringExtra("desc");
        genre = data.getStringExtra("genre");
        url = data.getStringExtra("url");
    }

}