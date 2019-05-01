package com.bullsheep.bullsheepfood_android.model;

import com.google.gson.annotations.SerializedName;

public class Nutrition {
    @SerializedName("ENERC_KCAL")
    private double kCal;

    @SerializedName("PROCNT")
    private double protein;

    @SerializedName("FAT")
    private double fat;

    @SerializedName("CHOCDF")
    private double carb;

    public Nutrition(double kCal, double protein, double fat, double carb) {
        this.kCal = kCal;
        this.protein = protein;
        this.fat = fat;
        this.carb = carb;
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

    public double getCarb() {
        return carb;
    }

    public void setCarb(double carb) {
        this.carb = carb;
    }
}
