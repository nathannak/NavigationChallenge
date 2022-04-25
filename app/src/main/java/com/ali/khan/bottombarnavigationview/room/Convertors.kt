package com.ali.khan.bottombarnavigationview.room

import androidx.room.TypeConverter
import com.ali.khan.bottombarnavigationview.model.Rating

class Converters {

    @TypeConverter
    fun toRating(rating: String): Rating {
        return Rating(0,rating.toDouble())
    }

    @TypeConverter
    fun fromRating(rating: Rating): String {
        return rating.rate.toString()
    }
}