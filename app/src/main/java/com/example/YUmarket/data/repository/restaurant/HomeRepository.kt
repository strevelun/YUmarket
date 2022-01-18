package com.example.YUmarket.data.repository.restaurant

import com.example.YUmarket.model.homelist.HomeListModel
import com.example.YUmarket.screen.home.homelist.HomeCategory

interface HomeRepository {
    fun getList(homeCategory: HomeCategory) : List<HomeListModel>
}