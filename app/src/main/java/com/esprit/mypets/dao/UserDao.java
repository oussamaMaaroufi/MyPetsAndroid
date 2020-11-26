package com.esprit.mypets.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.esprit.mypets.entity.UserSharedpref;

import java.util.List;

@Dao
public interface UserDao  {

    @Query("SELECT * FROM UserSharedpref")
    List<UserSharedpref> getAll();

    @Query("SELECT * FROM UserSharedpref WHERE id IN (:userIds)")
    List<UserSharedpref> loadAllByIds(int[] userIds);
/*
    @Query("SELECT * FROM Personne WHERE nom LIKE :nom AND " +
            "prenom LIKE :nom LIMIT 1")
    Personne findByName(String nom, String prenom);
*/
    @Insert
    void insertAll(UserSharedpref... userSharedprefs);

    @Delete
    void delete(UserSharedpref... userSharedprefs);

}
