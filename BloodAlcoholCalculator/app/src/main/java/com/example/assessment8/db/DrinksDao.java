package com.example.assessment8.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;
@Dao
public interface DrinksDao {
    @Query("SELECT * FROM drink")
    List<Drink> getAll();

    @Query("SELECT * FROM drink where id IN(:drinkIds)")
    List<Drink> loadAllByIds(int[] drinkIds);

//    @Query("SELECT * FROM drink WHERE courseNumber LIKE :courseNumber LIMIT 1")
//    Grade findCourseNumber(String courseNumber);


    @Insert
    void insertAll(Drink... drinks);
    @Delete
    void delete(Drink drink);

    @Delete
    void deleteAll(ArrayList<Drink> drinks);
    @Update
    void update(Drink drink);
}
