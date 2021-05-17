package com.example.food;

public class restaurant {
    private String City,EmailID,Fname,House,Mobile,Password,Postcode, State;

    public restaurant(String city, String emailID, String fname, String house, String mobile, String password, String postcode, String state) {
        City = city;
        EmailID = emailID;
        Fname = fname;
        House = house;
        Mobile = mobile;
        Password = password;
        Postcode = postcode;
        State = state;
    }
public restaurant(){

}

    public String getCity() {
        return City;
    }

    public String getEmailID() {
        return EmailID;
    }

    public String getFname() {
        return Fname;
    }

    public String getHouse() {
        return House;
    }

    public String getMobile() {
        return Mobile;
    }

    public String getPassword() {
        return Password;
    }

    public String getPostcode() {
        return Postcode;
    }

    public String getState() {
        return State;
    }

    public void setCity(String city) {
        City = city;
    }

    public void setEmailID(String emailID) {
        EmailID = emailID;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public void setHouse(String house) {
        House = house;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setPostcode(String postcode) {
        Postcode = postcode;
    }

    public void setState(String state) {
        State = state;
    }
}
