package com.example.engineoilchangeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootView = findViewById(R.id.rootview);
//        rootView.setBackgroundColor(Color.DKGRAY);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.rootview, new ListFragment())
                .commit();
    }


}