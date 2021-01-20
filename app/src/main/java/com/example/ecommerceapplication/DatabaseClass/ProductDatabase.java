package com.example.ecommerceapplication.DatabaseClass;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {CartData.class},version = 1)
public abstract class ProductDatabase extends RoomDatabase {
    public abstract CartDao cartDao();
    public  static volatile ProductDatabase INSTANCE;
    static ProductDatabase getDatabase(final Context context){
        if(INSTANCE==null){
            synchronized (ProductDatabase.class){
                if (INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),ProductDatabase.class,"word_database").fallbackToDestructiveMigration().build();


                }
            }

        }
        return INSTANCE;
    }
}