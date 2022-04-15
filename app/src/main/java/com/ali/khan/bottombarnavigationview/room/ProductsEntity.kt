package com.ali.khan.bottombarnavigationview.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products_items")
data class ProductsEntity(
    var description: String,
    var image: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}