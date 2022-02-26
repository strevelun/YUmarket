package com.example.myapplication23.widget.adapter.listener.restaurant

import com.example.myapplication23.model.restaurant.RestaurantModel
import com.example.myapplication23.widget.adapter.listener.AdapterListener

interface RestaurantListListener : AdapterListener {
    fun onClickItem(model: RestaurantModel)
}