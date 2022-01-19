package com.example.YUmarket.screen.home.homemain

import androidx.annotation.StringRes
import com.example.YUmarket.model.homelist.TownMarketModel

sealed class HomeMainState {
    object Uninitialized : HomeMainState()

    object Loading : HomeMainState()

    data class Success(
        val marketModelList: List<TownMarketModel>
    ) : HomeMainState()

    data class Error(
        @StringRes val errorMessage: Int
    ) : HomeMainState()
}
