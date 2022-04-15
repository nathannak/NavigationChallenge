package com.ali.khan.bottombarnavigationview.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ali.khan.bottombarnavigationview.R
import com.ali.khan.bottombarnavigationview.model.ProductsItem
import com.squareup.picasso.Picasso

class ProductsRecyclerViewAdapter(list: MutableList<ProductsItem>, val fragment: Fragment1? = null) :
    RecyclerView.Adapter<ProductsRecyclerViewAdapter.ProductsViewHolder>() {

    var mList = mutableListOf<ProductsItem>()

    init {
        mList.clear()
        mList = list
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ProductsViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.products_recview, viewGroup, false)
        return ProductsViewHolder(fragment,v)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.itemDescription.setText(mList[position].description)
        Picasso.get().load(mList[position].image).into(holder.itemImage);
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ProductsViewHolder(val fragmnet: Fragment1?, v: View) : RecyclerView.ViewHolder(v), View.OnLongClickListener {
        init {
            v.setOnLongClickListener(this)
        }

        var itemDescription: TextView = v.findViewById(R.id.product_description)
        var itemImage: ImageView = v.findViewById(R.id.product_image)

        override fun onLongClick(v: View?): Boolean {
            fragmnet?.addToCart(itemDescription.text.toString() )
            return true
        }
    }
}