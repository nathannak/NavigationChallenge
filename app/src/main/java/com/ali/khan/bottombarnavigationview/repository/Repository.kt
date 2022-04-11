package com.ali.khan.bottombarnavigationview.repository

import android.content.Context
import com.ali.khan.bottombarnavigationview.data.Products
import com.ali.khan.bottombarnavigationview.view.DialogHelper.postErrordialog
import android.net.NetworkInfo
import android.net.ConnectivityManager
import java.lang.Exception


class Repository(val context: Context) {

    suspend fun fetchProductsFromRemote(): Products? {
        if(isNetWorkConnected(context)){
            val response = ProductService.getRetrofit().getProducts()
            if (response.isSuccessful) {
                return response.body()
            } else {
                postErrordialog(context)
            }
        }
        return null
    }

    private fun isNetWorkConnected(context: Context): Boolean {
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
