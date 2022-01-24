package com.example.YUmarket.data.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.YUmarket.data.db.dao.BasketDao
import com.example.YUmarket.data.entity.room.BasketEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BasketDaoTest {

    private lateinit var database: YUMarketDB
    private lateinit var basketDao: BasketDao
    private val testList = (1..10).map {
        BasketEntity(
            id = it.toString(),
            itemName = "Item Name $it",
            storeNameId = it.toLong(),
            amount = it
        )
    }

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context, YUMarketDB::class.java
        ).build()

        basketDao = database.basketDao
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun getAllItems_returnCorrect() = runBlocking {
        testList.forEach {
            basketDao.insertItem(it)
        }

        val result = basketDao.getAllItems()
        assertThat(result).isEqualTo(testList)
    }

    @Test
    fun deleteAllItems_returnEmpty() = runBlocking {
        testList.forEach {
            basketDao.insertItem(it)
        }

        val result = basketDao.getAllItems()
        assertThat(result).isEqualTo(testList)

        basketDao.deleteAllItems()
        val afterDeleteResult = basketDao.getAllItems()
        assertThat(afterDeleteResult).isEmpty()
    }
}