package com.example.myapplication23.screen.home

import androidx.annotation.StringRes
import com.example.myapplication23.R

enum class Category(
    @StringRes val categoryNameId: Int
) {
    ALL(R.string.all),
    FOOD(R.string.food),
    MART(R.string.mart),
    SERVICE(R.string.service),
    FASHION(R.string.fashion),
    ACCESSORY(R.string.accessory)
}