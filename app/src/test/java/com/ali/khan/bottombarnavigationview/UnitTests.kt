package com.ali.khan.bottombarnavigationview

import android.content.Context
import com.ali.khan.bottombarnavigationview.repository.Repository
import com.ali.khan.bottombarnavigationview.room.ProductsDao
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

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
}