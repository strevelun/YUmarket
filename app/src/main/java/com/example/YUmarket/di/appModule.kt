package com.example.YUmarket.di

import com.example.YUmarket.data.repository.map.DefaultMapRepository
import com.example.YUmarket.data.repository.map.MapRepository
import com.example.YUmarket.data.repository.restaurant.DefaultHomeRepository
import com.example.YUmarket.data.repository.restaurant.HomeRepository
import com.example.YUmarket.model.homelist.category.HomeListCategory
import com.example.YUmarket.screen.MainViewModel
import com.example.YUmarket.screen.home.homelist.HomeListViewModel
import com.example.YUmarket.screen.home.HomeViewModel
import com.example.YUmarket.screen.like.LikeViewModel
import com.example.YUmarket.screen.map.MapViewModel
import com.example.YUmarket.screen.myinfo.MyInfoViewModel
import com.example.YUmarket.screen.orderlist.OrderListViewModel
import com.example.YUmarket.util.provider.DefaultResourcesProvider
import com.example.YUmarket.util.provider.ResourcesProvider
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {

    viewModel { HomeViewModel() }

    factory { (homeListCategory: HomeListCategory) ->
        HomeListViewModel(homeListCategory, get())
    }

    viewModel { MainViewModel(get()) }
    viewModel { LikeViewModel() }
    viewModel { MapViewModel() }
    viewModel { MyInfoViewModel() }
    viewModel { OrderListViewModel() }

    single<HomeRepository> { DefaultHomeRepository() }

    single { buildOkHttpClient() }
    single { provideGsonConverterFactory() }

    single(named("map")) { provideMapRetrofit(get(), get()) }
    single { provideMapApiService(get(qualifier = named("map"))) }

    single<MapRepository> { DefaultMapRepository(get(), get()) }

    single<ResourcesProvider> { DefaultResourcesProvider(androidContext()) }

    single { Dispatchers.IO }
    single { Dispatchers.Main }

}