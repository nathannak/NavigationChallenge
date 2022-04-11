package com.ali.khan.bottombarnavigationview.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ali.khan.bottombarnavigationview.R
import com.ali.khan.bottombarnavigationview.viewmodel.ProductsViewModel

class Fragment1 : Fragment() {

    private val viewModel: ProductsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel.productList.observe(viewLifecycleOwner, Observer {
            //update rec view
                list ->
            Toast.makeText(context, list.size.toString(), Toast.LENGTH_LONG).show()
        })

        return inflater.inflate(R.layout.fragment1, container, false)
    }

}