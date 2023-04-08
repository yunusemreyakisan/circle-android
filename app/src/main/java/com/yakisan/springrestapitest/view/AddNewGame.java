package com.yakisan.springrestapitest.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.yakisan.springrestapitest.databinding.ActivityAddNewGameBinding;
import com.yakisan.springrestapitest.viewmodel.AddNewGameViewModel;

public class AddNewGame extends AppCompatActivity {
    ActivityAddNewGameBinding binding;
    AddNewGameViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNewGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //ViewModel Binding
        viewModel = ViewModelProviders.of(this).get(AddNewGameViewModel.class);
        //Oyunu kaydeder.
        viewModel.saveAndPostNewGame(binding, this);


    }


}