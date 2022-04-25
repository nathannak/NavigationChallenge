package com.ali.khan.bottombarnavigationview.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali.khan.bottombarnavigationview.model.Products
import com.ali.khan.bottombarnavigationview.model.ProductsItem
import com.ali.khan.bottombarnavigationview.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (val repo: Repository) : ViewModel() {

    internal val productList = MutableLiveData<Products>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            var products: Products? = null
                products = repo.fetchProductsFromRemote()
                if (products == null) {
                    repo.showError()
                } else {
                    productList.postValue(products)
                }
            }
        }

    fun addToCart(productsItem: ProductsItem) {
        viewModelScope.launch {
            repo.insert(productsItem)
        }
    }

    var name = MutableLiveData<String>()
    val lastName = MutableLiveData<String>()

    fun writeToSharedPref(name: String, value: String) {
        repo.writeToSharedPref(name,value)
    }
}