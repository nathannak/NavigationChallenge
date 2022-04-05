package com.ali.khan.bottombarnavigationview.repository

import android.app.AlertDialog
import android.content.Context
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import android.content.DialogInterface
import androidx.lifecycle.LiveData
import com.ali.khan.bottombarnavigationview.data.Products
import com.ali.khan.bottombarnavigationview.data.ProductsItem
import com.ali.khan.bottombarnavigationview.view.DialogHelper.postErrordialog

class Repository(val context: Context) {

    suspend fun fetchProductsFromRemote(): Products? {
        val response = ProductService.getRetrofit().getProducts()
        if (response.isSuccessful) {
            return response.body()
        } else {
            postErrordialog(context)
        }
        return null
    }
}
