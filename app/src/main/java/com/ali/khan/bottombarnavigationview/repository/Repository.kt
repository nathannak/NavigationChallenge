package com.ali.khan.bottombarnavigationview.repository

import android.content.Context
import com.ali.khan.bottombarnavigationview.model.Products
import android.net.NetworkInfo
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.ali.khan.bottombarnavigationview.model.ProductsItem
import com.ali.khan.bottombarnavigationview.room.ProductsDao
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class Repository @Inject constructor (var context: Context, val productDao: ProductsDao? = null) {

    lateinit var response: Response<Products>

    suspend fun fetchProductsFromRemote(): Products? {
        if(isNetWorkConnected(context)){
            response = ProductService.getRetrofit().getProducts()
            if (response.isSuccessful) {
                return response.body()
            } else {
                showError()
            }
        }
        return null
    }

    internal suspend fun showError() {
        withContext(Dispatchers.Main) {
            Toast.makeText(context, "Error getting data from remote server", Toast.LENGTH_LONG)
                .show()
        }
    }

    val allProducts: LiveData<List<ProductsItem>>? = productDao?.getAll()


    suspend fun insert(product: ProductsItem) {
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

    internal fun writeToSharedPref(name: String, value: String) {
        val sharedPreference = context.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        var editor = sharedPreference?.edit()
        editor?.putString(name, value)
        editor?.commit()
    }

    internal fun getSharedPref() = context.getSharedPreferences("PREFERENCE_NAME",Context.MODE_PRIVATE)

}
