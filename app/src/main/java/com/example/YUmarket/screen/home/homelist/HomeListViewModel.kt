package com.example.YUmarket.screen.home.homelist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.YUmarket.data.repository.restaurant.HomeRepository
import com.example.YUmarket.model.homelist.HomeListModel
import com.example.YUmarket.screen.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeListViewModel(
    private val homeCategory: HomeCategory,
    private val homeRepository: HomeRepository
) : BaseViewModel() {
    val homeListData = MutableLiveData<List<HomeListModel>>()

    override fun fetchData(): Job = viewModelScope.launch {
        // TODO stub
        homeListData.value = homeRepository.getList(homeCategory)
    }
}