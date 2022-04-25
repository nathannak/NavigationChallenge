package com.ali.khan.bottombarnavigationview.di

import android.content.Context
import com.ali.khan.bottombarnavigationview.repository.Repository
import com.ali.khan.bottombarnavigationview.room.ProductsDao
import com.ali.khan.bottombarnavigationview.room.ProductsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRepository(@ApplicationContext context: Context, productsDao: ProductsDao) = Repository(
    context, productsDao)

    @Provides
    @Singleton
    fun provideProductDao(@ApplicationContext context: Context): ProductsDao {
        return ProductsDatabase.getInstance(context).productsDao()
    }

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

}