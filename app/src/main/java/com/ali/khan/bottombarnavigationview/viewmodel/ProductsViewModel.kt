package com.ali.khan.bottombarnavigationview.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ali.khan.bottombarnavigationview.data.Products
import com.ali.khan.bottombarnavigationview.data.ProductsItem
import com.ali.khan.bottombarnavigationview.repository.Repository
import kotlinx.coroutines.*

class ProductsViewModel(val app: Application): AndroidViewModel(app) {

    internal val productList = MutableLiveData<MutableList<ProductsItem>>()

    init {
        GlobalScope.launch {
            val products = Repository(app.applicationContext).fetchProductsFromRemote()
            if(products == null) {
                withContext(Dispatchers.Main){
                    Toast.makeText(app.applicationContext,"Error gettig data from remote server",Toast.LENGTH_LONG).show()
                }
            }else{
                val mList = mutableListOf<ProductsItem>()
                for(pi in products){
                    mList.add(pi)
                }
                productList.postValue(mList)
            }
        }
    }
}