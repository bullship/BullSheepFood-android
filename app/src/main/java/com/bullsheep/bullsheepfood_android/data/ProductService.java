package com.bullsheep.bullsheepfood_android.data;

import com.bullsheep.bullsheepfood_android.dto.ProductDTO;
import com.bullsheep.bullsheepfood_android.model.Product;
import com.bullsheep.bullsheepfood_android.model.RecipeResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductService {
    String API = "api/";

    @GET(API + "Food/{ingredientName}")
    Call<Response<RecipeResponse>> getFood(@Path("ingredientName") String name);

    @GET(API + "Product")
    Call<List<Product>> getAllProducts();

    @GET(API + "Product/{id}")
    Call<Product> getProduct(@Path("id") String id);

    @PUT(API + "Product")
    Call<Void> updateProduct(@Body Product product);

    @POST(API + "Product")
    Call<Product> create(@Body Product product);

}
