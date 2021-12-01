package com.lovehp30.clockgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.lovehp30.clockgame.databinding.ActivityCreditBinding;

public class CreditActivity extends AppCompatActivity {
    ActivityCreditBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnCreditBack.setOnClickListener(v->finish());
    }
}