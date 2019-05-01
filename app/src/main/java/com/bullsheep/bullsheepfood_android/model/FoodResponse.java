package com.bullsheep.bullsheepfood_android.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FoodResponse {

    @SerializedName("food")
    private Food food;

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
