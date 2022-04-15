package com.ali.khan.bottombarnavigationview.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ali.khan.bottombarnavigationview.R
import com.ali.khan.bottombarnavigationview.model.ProductsItem
import com.squareup.picasso.Picasso

class productsRecyclerViewAdapter(context: Context?, list: MutableList<ProductsItem>) : RecyclerView.Adapter<productsRecyclerViewAdapter.ProductsViewHolder>()  {

    var mList = mutableListOf<ProductsItem>()

    init {
        mList.clear()
        mList = list
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ProductsViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.products_recview, viewGroup, false)
        return ProductsViewHolder(v)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.itemDescription.setText(mList[position].description)
        Picasso.get().load(mList[position].image).into(holder.itemImage);
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ProductsViewHolder(v: View) : RecyclerView.ViewHolder(v),View.OnClickListener {

        private var view: View = v
        var itemDescription: TextView = v.findViewById(R.id.product_description)
        var itemImage: ImageView = v.findViewById(R.id.product_image)

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {

        }

        companion object {

        }
    }
}