package com.example.androidproject;

public class GroceryItem {

    private String name;
    private boolean purchased;

    public GroceryItem(String name, boolean purchased) {
        this.name = name;
        this.purchased = purchased;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }
}
