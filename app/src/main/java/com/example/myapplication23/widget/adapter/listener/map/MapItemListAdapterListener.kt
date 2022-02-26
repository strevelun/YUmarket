package com.example.myapplication23.widget.adapter.listener.map

import com.example.myapplication23.model.map.MapItemModel
import com.example.myapplication23.model.restaurant.RestaurantModel

interface MapItemListAdapterListener {
    fun onClickItem(model: MapItemModel)
}