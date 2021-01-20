package com.example.ecommerceapplication.DatabaseClass;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ProductRepository {
    private CartDao cartDao;
    private LiveData<List<CartData>> cartData;

    public ProductRepository(Application application) {
        ProductDatabase database=ProductDatabase.getDatabase(application);
        cartDao=database.cartDao();
        cartData=cartDao.getData();
    }
    public CartData findProduct(String id){
        CartData cartData=cartDao.isProductPresent(id);
        return cartData;
    }
    public LiveData<List<CartData>> listLiveData(){
        return cartData;
    }
    public void insertData(CartData cartData){
        new MyTask(cartDao).execute(cartData);

    }
    public  void deleteData(CartData cartData){
        new MyDeleteTask(cartDao).execute(cartData);

    }

    public class MyTask extends AsyncTask<CartData,Void,Void> {
        public CartDao dao;

        public MyTask(CartDao cartDao) {
            dao=cartDao;
        }

        @Override
        protected Void doInBackground(CartData... cartData) {
            dao.insertData(cartData[0]);
            return null;
        }
    }

    public  class MyDeleteTask extends AsyncTask<CartData,Void,Void> {
        public CartDao cdao;
        public MyDeleteTask(CartDao cartDao) {
            cdao=cartDao;
        }

        @Override
        protected Void doInBackground(CartData... cartData) {
            cdao.deleteData(cartData[0]);
            return null;
        }
    }


}