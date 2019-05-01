package com.bullsheep.bullsheepfood_android.dto;

import androidx.annotation.NonNull;

public class ProductDTO {
    private String name;
    private float proteins;
    private float fats;
    private float carbs;
    private float cal;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getProteins() {
        return proteins;
    }

    public void setProteins(float proteins) {
        this.proteins = proteins;
    }

    public float getFats() {
        return fats;
    }

    public void setFats(float fats) {
        this.fats = fats;
    }

    public float getCarbs() {
        return carbs;
    }

    public void setCarbs(float carbs) {
        this.carbs = carbs;
    }

    public float getCal() {
        return cal;
    }

    public void setCal(float cal) {
        this.cal = cal;
    }

    @NonNull
    @Override
    public String toString() {
        return "Name: " + name
                + "\nProteins: " + proteins
                + "\nFats: " + fats
                + "\nCarbs: " + carbs
                + "\nCal: " + cal + '\n';
    }
}
