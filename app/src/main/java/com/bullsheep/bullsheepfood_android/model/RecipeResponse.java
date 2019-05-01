package com.bullsheep.bullsheepfood_android.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecipeResponse {

    @SerializedName("text")
    private String text;

    @SerializedName("hints")
    private List<FoodResponse> hints;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<FoodResponse> getHints() {
        return hints;
    }

    public void setHints(List<FoodResponse> hints) {
        this.hints = hints;
    }
}
