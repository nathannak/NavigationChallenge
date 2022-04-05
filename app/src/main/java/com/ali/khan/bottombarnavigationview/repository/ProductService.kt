package com.ali.khan.bottombarnavigationview.repository

import com.ali.khan.bottombarnavigationview.data.Products
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ProductService {
    fun getRetrofit(): ProductsApi {
        return Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductsApi::class.java)
    }
}