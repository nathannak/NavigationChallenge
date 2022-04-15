package com.ali.khan.bottombarnavigationview.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductsDao {
    @Query("SELECT DISTINCT * FROM products_items GROUP BY description")
    fun getAll(): LiveData<List<ProductsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg product: ProductsEntity)

    @Delete
    suspend fun delete(product: ProductsEntity)
}