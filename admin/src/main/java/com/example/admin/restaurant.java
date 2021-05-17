package com.example.admin;

import android.widget.EditText;

public class restaurant {
    private String City,EmailID,Fname,House,Mobile,Password,Postcode, State;
    public restaurant( String city, String emailID, String fname, String house, String mobile, String password, String postcode, String state){
        City=city;
        EmailID=emailID;
        Fname=fname;
        House=house;
        Mobile=mobile;
        Password=password;
        Postcode=postcode;
        State=state;
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
}
