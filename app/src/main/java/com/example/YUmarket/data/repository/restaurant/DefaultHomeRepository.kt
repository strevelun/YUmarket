package com.example.YUmarket.data.repository.restaurant

import com.example.YUmarket.model.homelist.HomeListModel
import com.example.YUmarket.screen.home.homelist.HomeCategory

class DefaultHomeRepository : HomeRepository {
    override fun getList(homeCategory: HomeCategory): List<HomeListModel> {
        // TODO Delete category all?
        val mockList = listOf(
            HomeListModel(
                1, "all1", HomeCategory.ALL
            ),
            HomeListModel(
                1, "all2", HomeCategory.ALL
            ),
            HomeListModel(
                1, "all3", HomeCategory.ALL
            ),
            HomeListModel(
                1, "all4", HomeCategory.ALL
            ),
            HomeListModel(
                1, "all5", HomeCategory.ALL
            ),
            HomeListModel(
                1, "all6", HomeCategory.ALL
            ),
            HomeListModel(
                1, "all7", HomeCategory.ALL
            ),


            HomeListModel(
                1, "food1", HomeCategory.FOOD
            ),
            HomeListModel(
                1, "food2", HomeCategory.FOOD
            ),
            HomeListModel(
                1, "food3", HomeCategory.FOOD
            ),
            HomeListModel(
                1, "food4", HomeCategory.FOOD
            ),
            HomeListModel(
                1, "food5", HomeCategory.FOOD
            ),
            HomeListModel(
                1, "food6", HomeCategory.FOOD
            ),
            HomeListModel(
                1, "food7", HomeCategory.FOOD
            ),

            HomeListModel(
                1, "mart1", HomeCategory.MART
            ),
            HomeListModel(
                1, "mart2", HomeCategory.MART
            ),
            HomeListModel(
                1, "mart3", HomeCategory.MART
            ),
            HomeListModel(
                1, "mart4", HomeCategory.MART
            ),
            HomeListModel(
                1, "mart5", HomeCategory.MART
            ),
            HomeListModel(
                1, "mart6", HomeCategory.MART
            ),
            HomeListModel(
                1, "mart7", HomeCategory.MART
            ),

            
            HomeListModel(
                1, "service1", HomeCategory.SERVICE
            ),
            HomeListModel(
                1, "service2", HomeCategory.SERVICE
            ),
            HomeListModel(
                1, "service3", HomeCategory.SERVICE
            ),
            HomeListModel(
                1, "service4", HomeCategory.SERVICE
            ),
            HomeListModel(
                1, "service5", HomeCategory.SERVICE
            ),
            HomeListModel(
                1, "service6", HomeCategory.SERVICE
            ),
            HomeListModel(
                1, "service7", HomeCategory.SERVICE
            ),


            HomeListModel(
                1, "fashion1", HomeCategory.FASHION
            ),
            HomeListModel(
                1, "fashion2", HomeCategory.FASHION
            ),
            HomeListModel(
                1, "fashion3", HomeCategory.FASHION
            ),
            HomeListModel(
                1, "fashion4", HomeCategory.FASHION
            ),
            HomeListModel(
                1, "fashion5", HomeCategory.FASHION
            ),
            HomeListModel(
                1, "fashion6", HomeCategory.FASHION
            ),
            HomeListModel(
                1, "fashion7", HomeCategory.FASHION
            ),


            HomeListModel(
                1, "accessory1", HomeCategory.ACCESSORY
            ),
            HomeListModel(
                1, "accessory2", HomeCategory.ACCESSORY
            ),
            HomeListModel(
                1, "accessory3", HomeCategory.ACCESSORY
            ),
            HomeListModel(
                1, "accessory4", HomeCategory.ACCESSORY
            ),
            HomeListModel(
                1, "accessory5", HomeCategory.ACCESSORY
            ),
            HomeListModel(
                1, "accessory6", HomeCategory.ACCESSORY
            ),
            HomeListModel(
                1, "accessory7", HomeCategory.ACCESSORY
            ),
        )


        return mockList.filter { it.category == homeCategory }
    }
}