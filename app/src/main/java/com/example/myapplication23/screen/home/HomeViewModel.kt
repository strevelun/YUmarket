package com.example.myapplication23.screen.home

import androidx.lifecycle.viewModelScope
import com.example.myapplication23.data.repository.map.MapRepository
import com.example.myapplication23.screen.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

// RestaurantActivity ViewModel
class HomeViewModel : BaseViewModel() {

    companion object {
        val MY_LOCATION_KEY = "MyLocation"
    }

    override fun fetchData(): Job = viewModelScope.launch {
        // TODO get from repository
    }


}