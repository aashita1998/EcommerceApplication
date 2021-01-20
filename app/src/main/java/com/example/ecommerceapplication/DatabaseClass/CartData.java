package com.example.ecommerceapplication.DatabaseClass;

import com.example.ecommerceapplication.model.Products;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "DataTable")
public class  CartData {
    @PrimaryKey
    @NonNull
    public String ProductId;
    public  String ProductName;
    public String ProductPrice;
    public Integer imageUrl;
    public String ProductSpecification;

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(String productPrice) {
        ProductPrice = productPrice;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProductSpecification() {
        return ProductSpecification;
    }

    public void setProductSpecification(String productSpecification) {
        ProductSpecification = productSpecification;
    }
}
