package com.example.YUmarket.util

import androidx.annotation.StringRes
import com.example.YUmarket.data.entity.location.MapSearchInfoEntity

sealed class LocationState {
    object Uninitialized : LocationState()
    object Loading : LocationState()

    data class Success(
        val mapSearchInfoEntity: MapSearchInfoEntity,
        val isLocationSame: Boolean,
//        val foodMenuListInBasket: List<RestaurantFoodEntity>? = null
    ) : LocationState()

    data class Error(
        @StringRes val errorMessage: Int
    ) : LocationState()
}
