package com.example.YUmarket.data.repository.basket

import com.example.YUmarket.data.db.dao.BasketDao
import com.example.YUmarket.data.entity.room.BasketEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DefaultBasketRepository(
    private val ioDispatcher: CoroutineDispatcher,
    private val basketDao: BasketDao
) : BasketRepository {
    override suspend fun getAllItems(): List<BasketEntity> = withContext(ioDispatcher) {
        basketDao.getAllItems()
    }

    override suspend fun insertItem(item: BasketEntity) = withContext(ioDispatcher) {
        basketDao.insertItem(item)
    }

    override suspend fun deleteAllItems() = withContext(ioDispatcher) {
        basketDao.deleteAllItems()
    }
}