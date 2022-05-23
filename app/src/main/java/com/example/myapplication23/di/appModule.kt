package com.example.myapplication23.di

import aop.fastcampus.part6.chapter01.screen.mylocation.MyLocationViewModel
import com.example.myapplication23.data.entity.location.MapSearchInfoEntity
import com.example.myapplication23.data.repository.map.DefaultMapRepository
import com.example.myapplication23.data.repository.map.MapRepository
import com.example.myapplication23.data.repository.restaurant.DefaultRestaurantRepository
import com.example.myapplication23.data.repository.restaurant.RestaurantRepository
import com.example.myapplication23.screen.MainViewModel
import com.example.myapplication23.screen.home.HomeViewModel
import com.example.myapplication23.screen.home.restaurant.RestaurantCategory
import com.example.myapplication23.screen.home.restaurant.RestaurantListViewModel
import com.example.myapplication23.screen.like.LikeViewModel
import com.example.myapplication23.screen.map.MapLocationSetting.MapLocationSettingViewModel
import com.example.myapplication23.screen.map.MapViewModel
import com.example.myapplication23.screen.myinfo.MyInfoViewModel
import com.example.myapplication23.screen.orderlist.OrderListViewModel
import com.example.YUmarket.util.provider.DefaultResourcesProvider
import com.example.YUmarket.util.provider.ResourcesProvider
import com.example.myapplication23.data.db.YUMarketDB
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    viewModel { HomeViewModel() }

    factory { (restaurantCategory: RestaurantCategory) ->
        RestaurantListViewModel(restaurantCategory, get()) }

    viewModel { MainViewModel(get()) }
    viewModel { LikeViewModel() }
    viewModel { MapViewModel() }
    viewModel { MyInfoViewModel() }
    viewModel { OrderListViewModel() }
    viewModel { (mapSearchInfoEntity: MapSearchInfoEntity) ->
        MyLocationViewModel(mapSearchInfoEntity, get()) }
    viewModel { MapLocationSettingViewModel(get()) }

    single<RestaurantRepository> { DefaultRestaurantRepository() }

    single { buildOkHttpClient() }
    single { provideGsonConverterFactory() }

    single(named("map")) { provideMapRetrofit(get(), get()) }
    single { provideMapApiService(get(qualifier = named("map"))) }

    single<MapRepository> { DefaultMapRepository(get(), get()) }
    single<ResourcesProvider> { DefaultResourcesProvider(androidContext()) }

    single { Dispatchers.IO }

    single { provideDatabase(androidContext()) }
    single { provideAddressHistoryDao(get()) }
}