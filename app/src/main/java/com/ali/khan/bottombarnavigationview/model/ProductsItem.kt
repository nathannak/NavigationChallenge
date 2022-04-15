package com.ali.khan.bottombarnavigationview.model

data class ProductsItem(
    val category: String ?=null,
    val description: String,
    val id: Int ?=null,
    val image: String,
    val price: Double ?=null,
    val rating: Rating ?=null,
    val title: String ?=null
)