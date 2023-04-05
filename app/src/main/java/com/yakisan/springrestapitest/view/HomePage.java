package com.yakisan.springrestapitest.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.yakisan.springrestapitest.R;
import com.yakisan.springrestapitest.databinding.ActivityHomepageBinding;
import com.yakisan.springrestapitest.model.User;
import com.yakisan.springrestapitest.viewmodel.HomePageViewModel;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class HomePage extends AppCompatActivity {
    List<User> users = new ArrayList<>();
    ActivityHomepageBinding binding;
    HomePageViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomepageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //Viewmodel Binding
        viewModel = ViewModelProviders.of(this).get(HomePageViewModel.class);
        viewModel.getAllUsers(getApplicationContext());


    }

}