package com.bullsheep.bullsheepfood_android.model;

import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("productId")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("energyKcal")
    private double kCal;

    @SerializedName("protein")
    private double protein;

    @SerializedName("fat")
    private double fat;

    @SerializedName("carbohydrate")
    private double carbohydrate;

    @SerializedName("imageUri")
    private double imageUri;

    public Product(int id, String name, double kCal, double protein,
                   double fat, double carbohydrate, double imageUri) {
        this.id = id;
        this.name = name;
        this.kCal = kCal;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrate = carbohydrate;
        this.imageUri = imageUri;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getkCal() {
        return kCal;
    }

    public void setkCal(double kCal) {
        this.kCal = kCal;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(double carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public double getImageUri() {
        return imageUri;
    }

    public void setImageUri(double imageUri) {
        this.imageUri = imageUri;
    }
}
