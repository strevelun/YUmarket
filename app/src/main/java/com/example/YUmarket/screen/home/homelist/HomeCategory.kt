package com.example.YUmarket.screen.home.homelist

import androidx.annotation.StringRes
import com.example.YUmarket.R

enum class HomeCategory(
    @StringRes val categoryNameId: Int,
    @StringRes val categoryTypeId: Int
) {
    ALL(R.string.all, R.string.all_type),
    FOOD(R.string.food, R.string.food_type),
    MART(R.string.mart, R.string.mart_type),
    SERVICE(R.string.service, R.string.service_type),
    FASHION(R.string.fashion, R.string.fashion_type),
    ACCESSORY(R.string.accessory, R.string.accessory_type)
}