package com.bullsheep.bullsheepfood_android.dto;

import com.bullsheep.bullsheepfood_android.model.Product;

import java.util.List;

public class RationDTO {
    private String imageUrl;
    private String title;
    private List<Product> products;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
