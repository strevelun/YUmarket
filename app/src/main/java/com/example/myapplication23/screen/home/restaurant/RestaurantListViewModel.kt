package com.example.myapplication23.screen.home.restaurant

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication23.data.repository.restaurant.RestaurantRepository
import com.example.myapplication23.model.restaurant.RestaurantModel
import com.example.myapplication23.screen.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RestaurantListViewModel(
    private val restaurantCategory: RestaurantCategory,
    private val restaurantRepository: RestaurantRepository
) : BaseViewModel() {

//    private val _restaurantData : MutableLiveData<List<RestaurantModel>> = MutableLiveData<List<RestaurantModel>>()
//    val restaurantData : LiveData<List<RestaurantModel>> = _restaurantData

    val restaurantData : MutableLiveData<List<RestaurantModel>> = MutableLiveData<List<RestaurantModel>>()


    override fun fetchData(): Job = viewModelScope.launch {
        val list = restaurantRepository.getList(restaurantCategory)

        Log.d("TAG", "fetchData: $list")

        restaurantData.value = list
    }
}