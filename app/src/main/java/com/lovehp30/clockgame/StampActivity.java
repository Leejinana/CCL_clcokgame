package com.lovehp30.clockgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.lovehp30.clockgame.databinding.ActivityStampBinding;

public class StampActivity extends AppCompatActivity {

    ActivityStampBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStampBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnStampBack.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(),StartActivity.class));
            finish();
        });
    }
}