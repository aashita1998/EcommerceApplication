package com.example.ecommerceapplication;

public class ShippingHelperClass {

    String PostalName,PhoneNumber,Address,City,State,Pincode;

    public ShippingHelperClass(String postalName, String phoneNumber, String address, String city, String state, String pincode) {
        PostalName = postalName;
        PhoneNumber = phoneNumber;
        Address = address;
        City = city;
        State = state;
        Pincode = pincode;
    }

    public String getPostalName() {
        return PostalName;
    }

    public void setPostalName(String postalName) {
        PostalName = postalName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getPincode() {
        return Pincode;
    }

    public void setPincode(String pincode) {
        Pincode = pincode;
    }
}
