package com.example.ecommerceapplication.DatabaseClass;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class DataModel extends AndroidViewModel {
    ProductRepository productRepository;
    private LiveData<List<CartData>> listLiveData;

    public DataModel(@NonNull Application application) {
        super(application);
        productRepository=new ProductRepository(application);
        this.listLiveData=productRepository.listLiveData();
    }
    public CartData cartData(String id){
        CartData cartData=productRepository.findProduct(id);
        return  cartData;
    }
    public LiveData<List<CartData>> getListLiveData(){
        return listLiveData;
    }
    public void insert(CartData cartData){
        productRepository.insertData(cartData);

    }
    public void deletedata(CartData cartData){
        productRepository.deleteData(cartData);

    }



}
