package com.ali.khan.bottombarnavigationview.repository

import com.ali.khan.bottombarnavigationview.data.Products
import retrofit2.Response
import retrofit2.http.GET

interface ProductsApi {
    @GET("/products")
    suspend fun getProducts(): Response<Products>
}
