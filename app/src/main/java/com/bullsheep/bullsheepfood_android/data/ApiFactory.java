package com.bullsheep.bullsheepfood_android.data;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory {
    private static final String API_ENDPOINT = "http://bullsheepfood.azurewebsites.net/";
    private static volatile ProductService productService;

    @NonNull
    public static ProductService getProductService() {
        if (productService == null) {
            synchronized (ApiFactory.class) {
                if (productService == null) {
                    productService = new Retrofit.Builder()
                            .baseUrl(API_ENDPOINT)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .client(buildOkHttpClient())
                            .build()
                            .create(ProductService.class);
                }
            }
        }
        return productService;
    }

    private static OkHttpClient buildOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(12, TimeUnit.SECONDS)
                .readTimeout(12,TimeUnit.SECONDS)
                .addInterceptor(createLogInterceptor())
                .build();
    }

    private static HttpLoggingInterceptor createLogInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

}