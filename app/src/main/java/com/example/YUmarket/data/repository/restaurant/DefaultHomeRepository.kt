package com.example.YUmarket.data.repository.restaurant

import com.example.YUmarket.model.homelist.HomeListModel
import com.example.YUmarket.screen.home.homelist.HomeCategory

class DefaultHomeRepository : HomeRepository {
    override fun getList(homeCategory: HomeCategory): List<HomeListModel> {
        // TODO Delete category all?

        // create mock data
        return (1L..10L).map {
            HomeListModel(
                id = it,
                title = "$homeCategory$it",
                category = homeCategory
            )
        }
    }
}