package com.example.food.Adminorder;

public class FoodSupplyDetails {
    public String Dishes,Quantity,Price,Description,ImageURL,RandomUID,restaurantId;

    public FoodSupplyDetails(String dishes, String quantity, String price, String description, String imageURL, String randomUID, String restaurantId) {
        Dishes = dishes;
        Quantity = quantity;
        Price = price;
        Description = description;
        ImageURL = imageURL;
        RandomUID = randomUID;
        this.restaurantId = restaurantId;
    }
}
