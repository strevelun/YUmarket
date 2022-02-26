package com.example.myapplication23.data.repository.restaurant

import com.example.myapplication23.model.restaurant.RestaurantModel
import com.example.myapplication23.screen.home.restaurant.RestaurantCategory

interface RestaurantRepository {
    // TODO Entity?
    fun getList(restaurantCategory: RestaurantCategory) : List<RestaurantModel>
}