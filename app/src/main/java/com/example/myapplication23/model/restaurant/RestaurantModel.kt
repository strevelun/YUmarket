package com.example.myapplication23.model.restaurant

import com.example.myapplication23.model.CellType
import com.example.myapplication23.model.Model
import com.example.myapplication23.screen.home.restaurant.RestaurantCategory

class RestaurantModel(
    override val id: Long,
    val title: String,
    val category: RestaurantCategory,
    // TODO add more
    override val type: CellType = CellType.RESTAURANT_CELL,
) : Model(id, type){

}