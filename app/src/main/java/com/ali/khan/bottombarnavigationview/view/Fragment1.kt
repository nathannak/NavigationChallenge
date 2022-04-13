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
import com.ali.khan.bottombarnavigationview.viewmodel.ProductsViewModel

class Fragment1 : Fragment() {

    private val viewModel: ProductsViewModel by viewModels()
    private lateinit var recView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment1, container, false)
        recView = view.findViewById(R.id.products_recview)
        viewModel.productList.observe(viewLifecycleOwner, Observer {
            //update rec view
            list ->
            run {
                recView.adapter = productsRecyclerViewAdapter(context,list)
                recView.layoutManager = LinearLayoutManager(this.context)
                Toast.makeText(context, list.size.toString(), Toast.LENGTH_LONG).show()
            }
        })
        return view
    }
}