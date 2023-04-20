package com.example.assessment8.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Drink.class}, version = 4)
public abstract class AppDB extends RoomDatabase {

    public abstract DrinksDao drinksDao();




}
