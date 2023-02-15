package com.example.inclass05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements AppCatagoriesFragment.AppCategoriesFragmentListener, AppListsFragment.AppListsFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootview, new AppCatagoriesFragment())
                .commit();
    }

    @Override
    public void sendSelectedCategory(String category) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootview, AppListsFragment.newInstance(category))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void sendSelectedAppList(DataServices.App app) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootview, AppDetailsFragment.newInstance(app))
                .addToBackStack(null)
                .commit();
    }
}