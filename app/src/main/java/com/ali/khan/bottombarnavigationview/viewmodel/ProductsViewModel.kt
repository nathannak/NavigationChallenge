package com.ali.khan.bottombarnavigationview.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ali.khan.bottombarnavigationview.data.ProductsItem
import com.ali.khan.bottombarnavigationview.repository.Repository
import com.ali.khan.bottombarnavigationview.view.DialogHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProductsViewModel(val app: Application): AndroidViewModel(app) {

    internal val productList = MutableLiveData<MutableList<ProductsItem>>()

    init {
        GlobalScope.launch {
            val products = Repository(app.applicationContext).fetchProductsFromRemote()
            if(products == null) {
                DialogHelper.postErrordialog(app.applicationContext)
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