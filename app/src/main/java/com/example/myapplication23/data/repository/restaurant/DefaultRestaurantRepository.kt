package com.example.myapplication23.data.repository.restaurant

import com.example.myapplication23.model.restaurant.RestaurantModel
import com.example.myapplication23.screen.home.restaurant.RestaurantCategory

class DefaultRestaurantRepository : RestaurantRepository {
    override fun getList(restaurantCategory: RestaurantCategory): List<RestaurantModel> {
        return listOf(
            RestaurantModel(
                1, "title1", RestaurantCategory.ALL
            ),
            RestaurantModel(
                1, "title2", RestaurantCategory.ALL
            ),
            RestaurantModel(
                1, "title3", RestaurantCategory.ALL
            ),
            RestaurantModel(
                1, "title4", RestaurantCategory.ALL
            ),
            RestaurantModel(
                1, "title5", RestaurantCategory.ALL
            ),
            RestaurantModel(
                1, "title6", RestaurantCategory.ALL
            ),
            RestaurantModel(
                1, "title7", RestaurantCategory.ALL
            ),
        )
    }
}