package com.example.mvvmclean.common.datalayer.local;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

//@Database(entities = {}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;


    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "Mvvm-Example-Database")
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
