package com.ali.khan.bottombarnavigationview.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ali.khan.bottombarnavigationview.R
import com.ali.khan.bottombarnavigationview.model.ProductsItem
import com.ali.khan.bottombarnavigationview.repository.Repository
import com.ali.khan.bottombarnavigationview.room.ProductsDatabase
import com.ali.khan.bottombarnavigationview.viewmodel.ProductsViewModel

class Fragment4 : Fragment() {

    private val viewModel: ProductsViewModel by viewModels()
    private lateinit var recView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment4, container, false)
        recView = view.findViewById(R.id.cart_recview)
        viewModel.repo.allProducts?.observe(viewLifecycleOwner, Observer {
            //update rec view
                list ->
            run {
                val mList = mutableListOf<ProductsItem>()
                for(l in list) {
                    mList.add(ProductsItem(description = l.description, image = l.image))
                }
                recView.adapter = productsRecyclerViewAdapter(context, mList)
                recView.layoutManager = LinearLayoutManager(this.context)
            }
        })

        return view
    }

}