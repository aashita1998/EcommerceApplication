package com.example.ecommerceapplication.DatabaseClass;


import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface CartDao {
    @Insert
    void insertData(CartData cartData);
    @Query("SELECT * FROM DataTable")
    LiveData<List<CartData>> getData();
    @Delete
    void deleteData(CartData cartData);
    @Query("SELECT * FROM DataTable WHERE ProductId== :id")
    CartData isProductPresent(String id);


}