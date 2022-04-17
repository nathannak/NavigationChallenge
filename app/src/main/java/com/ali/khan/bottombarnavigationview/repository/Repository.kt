package com.ali.khan.bottombarnavigationview.repository

import android.content.Context
import com.ali.khan.bottombarnavigationview.model.Products
import android.net.NetworkInfo
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.ali.khan.bottombarnavigationview.room.ProductsDao
import com.ali.khan.bottombarnavigationview.room.ProductsEntity
import java.lang.Exception

class Repository(var context: Context, val productDao: ProductsDao? = null) {

    suspend fun fetchProductsFromRemote(): Products? {
        if(isNetWorkConnected(context)){
            val response = ProductService.getRetrofit().getProducts()
            if (response.isSuccessful) {
                return response.body()
            } else {
                Toast.makeText(context,"Error getting data from remote server", Toast.LENGTH_LONG).show()
            }
        }
        return null
    }

    val allProducts: LiveData<List<ProductsEntity>>? = productDao?.getAll()

    suspend fun insert(product: ProductsEntity) {
            productDao?.insert(product)
    }

    internal fun isNetWorkConnected(context: Context): Boolean {
        var status = false
        try {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            var netInfo = cm?.getNetworkInfo(0)
            if (netInfo != null && netInfo.state == NetworkInfo.State.CONNECTED) {
                status = true
            } else {
                netInfo = cm?.getNetworkInfo(1)
                if (netInfo != null && netInfo.state == NetworkInfo.State.CONNECTED) status = true
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
        return status
    }
}
