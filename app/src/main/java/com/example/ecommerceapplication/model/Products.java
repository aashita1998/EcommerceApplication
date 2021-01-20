package com.example.ecommerceapplication.model;

public class Products {

    String ProductId;
    String ProductName;
    String ProductPrice;
    Integer imageUrl;
    String ProductSpecification;


    public Products(String productId, String productName, String productPrice, Integer imageUrl, String productSpecification) {
        ProductId = productId;
        ProductName = productName;
        ProductPrice = productPrice;
        this.imageUrl = imageUrl;
        ProductSpecification = productSpecification;
    }

    public String getProductSpecification() {
        return ProductSpecification;
    }

    public void setProductSpecification(String productSpecification) {
        ProductSpecification = productSpecification;
    }

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
}
