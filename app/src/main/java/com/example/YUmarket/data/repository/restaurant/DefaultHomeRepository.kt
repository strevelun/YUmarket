package com.example.YUmarket.data.repository.restaurant

import com.example.YUmarket.model.homelist.HomeListModel
import com.example.YUmarket.screen.home.homelist.HomeCategory
import java.util.*

class DefaultHomeRepository : HomeRepository {
    override fun getList(homeCategory: HomeCategory): List<HomeListModel> {
        // TODO Delete category all?

        val random = Random()

        // create mock data
        // TODO 22.01.18 remove random create logics
        return (1L..10L).map {
            HomeListModel(
                id = it,
                title = "$homeCategory$it",
                category = homeCategory,
                isMarketOpen = random.nextBoolean(),
                distance = random.nextFloat() * 2 // 2km까지
            )
        }
    }
}