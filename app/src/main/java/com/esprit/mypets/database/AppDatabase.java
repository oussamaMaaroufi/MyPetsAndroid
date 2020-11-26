package com.esprit.mypets.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.esprit.mypets.dao.UserDao;
import com.esprit.mypets.entity.UserSharedpref;

@Database(entities = {UserSharedpref.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;
    public abstract UserDao userDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "database-MyPets")
                            .allowMainThreadQueries()
                          //  .addCallback(prepopulateDataBase())
                            .build();
                }
            }

        }
        return INSTANCE;
    }
}

