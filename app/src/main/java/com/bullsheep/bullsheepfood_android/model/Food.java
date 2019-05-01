package com.bullsheep.bullsheepfood_android.model;

import com.google.gson.annotations.SerializedName;

public class Food {

    private String foodId;
    private String label;

    @SerializedName("nutrients")
    private Nutrition nutrition;
    private String source;

    public Food(String foodId, String label, Nutrition nutrition, String source) {
        this.foodId = foodId;
        this.label = label;
        this.nutrition = nutrition;
        this.source = source;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Nutrition getNutrition() {
        return nutrition;
    }

    public void setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
/**
 * "food": {
 *         "foodId": "food_bsdke1gbxpcmg3aps6aw5bc1ylov",
 *         "label": "Gmills Coc Puffs Frstd Corn Puffs Cereal",
 *
 *         "source": null
 *       }
 */
