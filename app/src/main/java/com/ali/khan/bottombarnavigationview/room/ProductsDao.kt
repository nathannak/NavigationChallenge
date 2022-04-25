package com.ali.khan.bottombarnavigationview.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ali.khan.bottombarnavigationview.model.ProductsItem

@Dao
interface ProductsDao {
    @Query("SELECT DISTINCT * FROM products_items GROUP BY description")
    fun getAll(): LiveData<List<ProductsItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg product: ProductsItem)

    @Delete
    suspend fun delete(product: ProductsItem)
}