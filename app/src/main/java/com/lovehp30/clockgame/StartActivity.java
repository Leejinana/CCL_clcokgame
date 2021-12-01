package com.lovehp30.clockgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.lovehp30.clockgame.databinding.ActivityStartBinding;

public class StartActivity extends AppCompatActivity {

    private ActivityStartBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCredit.setOnClickListener(v->{
           startActivity(new Intent(getApplicationContext(),CreditActivity.class));
        });
        binding.btnPlay.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        });
        binding.btnStamp.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(),StampActivity.class));
            finish();
        });
    }
}