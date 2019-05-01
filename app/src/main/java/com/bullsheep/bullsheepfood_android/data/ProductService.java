package com.bullsheep.bullsheepfood_android.data;

import com.bullsheep.bullsheepfood_android.model.Product;
import com.bullsheep.bullsheepfood_android.model.RecipeResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductService {
    String API = "api/";

    @GET(API + "Food/{ingredientName}")
    Observable<Response<RecipeResponse>> getFood(@Path("ingredientName") String name);

    @GET(API + "Product")
    Observable<List<Product>> getAllProducts();

    @GET(API + "Product/{id}")
    Observable<Product> getProduct(@Path("id") String id);

    @PUT(API + "Product")
    Observable<Void> updateProduct(@Body Product product);

    @POST(API + "Product")
    Observable<Product> create(@Body Product product);

}
