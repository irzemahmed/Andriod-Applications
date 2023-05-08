package edu.uncc.inclass09;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GradesDao {

    @Query("SELECT * FROM grade")
    List<Grade> getAll();

    @Query("SELECT * FROM grade where gid IN(:gradeIds)")
    List<Grade> loadAllByIds(int[] gradeIds);

    @Query("SELECT * FROM grade WHERE courseNumber LIKE :courseNumber LIMIT 1")
    Grade findCourseNumber(String courseNumber);


    @Insert
    void insertAll(Grade... grades);
    @Delete
    void delete(Grade grade);

}
