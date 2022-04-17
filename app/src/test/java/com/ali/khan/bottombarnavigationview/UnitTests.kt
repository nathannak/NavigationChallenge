package com.ali.khan.bottombarnavigationview

import android.content.Context
import com.ali.khan.bottombarnavigationview.model.Products
import com.ali.khan.bottombarnavigationview.repository.ProductService
import com.ali.khan.bottombarnavigationview.repository.Repository
import com.ali.khan.bottombarnavigationview.room.ProductsDao
import io.mockk.*
import io.mockk.every
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import retrofit2.Response

class UnitTests {

    @Test
    fun `fetchProductsFromRemote returns null when network not connected`() {

        val context = mockk<Context>(relaxed = true)
        val productsDao = mockk<ProductsDao>(relaxed = true)
        val repository = spyk(Repository(context,productsDao))

        repository.context = context
        every { repository.context} returns context
        every { repository invoke "isNetWorkConnected" withArguments listOf(repository.context) } answers {false}

        var result: Any? = null
        runBlocking {
            result = repository.fetchProductsFromRemote()
        }

        Thread.sleep(2000)
        Assert.assertNull(result)
    }

    @Test
    fun `fetchProductsFromRemote calls showError when network connected but response is not successful`() {

        val context = mockk<Context>(relaxed = true)
        val productsDao = mockk<ProductsDao>(relaxed = true)
        val repository = spyk(Repository(context,productsDao))

        val response = mockk<Response<Products>>(relaxed = true)

        repository.context = context
        repository.response = response

        every { repository.context} returns context
        every { repository invoke "isNetWorkConnected" withArguments listOf(context) } returns  true
        justRun { repository invoke "showError" withArguments listOf() }
        every { response.isSuccessful } returns false

        mockkObject(ProductService)
        coEvery { ProductService.getRetrofit().getProducts() }  returns response

        runBlocking {
            repository.fetchProductsFromRemote()
        }

        Thread.sleep(2000)
        verify { repository.showError() }
    }
}