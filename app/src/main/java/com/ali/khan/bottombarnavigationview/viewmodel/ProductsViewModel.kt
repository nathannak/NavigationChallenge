package com.ali.khan.bottombarnavigationview.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ali.khan.bottombarnavigationview.model.Products
import com.ali.khan.bottombarnavigationview.model.ProductsItem
import com.ali.khan.bottombarnavigationview.repository.Repository
import com.ali.khan.bottombarnavigationview.room.ProductsDatabase
import com.ali.khan.bottombarnavigationview.room.ProductsEntity
import kotlinx.coroutines.*

class ProductsViewModel(val app: Application) : AndroidViewModel(app) {

    internal val productList = MutableLiveData<MutableList<ProductsItem>>()
    internal val mList = mutableListOf<ProductsItem>()
    internal var repo: Repository

    init {
        repo = Repository(
            app.applicationContext,
            ProductsDatabase.getInstance(app.applicationContext).productsDao()
        )
        viewModelScope.launch(Dispatchers.IO) {
            var products: Products? = null
            withContext(Dispatchers.Main) {
                products = repo.fetchProductsFromRemote()
                if (products == null) {
                    Toast.makeText(
                        app.applicationContext,
                        "Error getting data from remote server",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    for (pi in products!!) {
                        mList.add(pi)
                    }
                    productList.postValue(mList)
                }
            }
        }
    }

    fun addToCart(description: String) {
        val pe = ProductsEntity("", "")
        for (l in mList) {
            if (l.description.equals(description, true)) {
                pe.description = description
                pe.image = l.image
            }
        }
        viewModelScope.launch {
            repo.insert(pe)
        }
    }
}