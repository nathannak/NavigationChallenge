package com.ali.khan.bottombarnavigationview.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ali.khan.bottombarnavigationview.R
import com.ali.khan.bottombarnavigationview.model.ProductsItem
import com.ali.khan.bottombarnavigationview.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Fragment4 : Fragment() {

    private val viewModel: MainViewModel by viewModels()
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
                //avoid this for loop use ProductsItem
                for(l in list) {
                    mList.add(ProductsItem(description = l.description, image = l.image))
                }
                recView.adapter = ProductsRecyclerViewAdapter(mList)
                recView.layoutManager = LinearLayoutManager(this.context)
            }
        })

        return view
    }

}

//TODO add product details (fragment)
//TODO you can use dialog fragment!