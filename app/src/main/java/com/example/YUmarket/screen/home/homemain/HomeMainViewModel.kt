package com.example.YUmarket.screen.home.homemain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.YUmarket.data.repository.restaurant.HomeRepository
import com.example.YUmarket.model.homelist.HomeListModel
import com.example.YUmarket.model.homelist.TownMarketModel
import com.example.YUmarket.screen.base.BaseViewModel
import com.example.YUmarket.util.LocationData
import com.example.YUmarket.util.LocationState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * [HomeMainFragment]에서 사용할 ViewModel
 * 기본적으로 Repository에서 Data를 가져온다.
 * @author Main 정남진, Sub 김건우, 김도엽, 배은호, 허희태
 * @since 2021.01.18
 * @param homeRepository 마켓 정보를 가져올 Repository
 */
class HomeMainViewModel(
    private val homeRepository: HomeRepository
    // TODO 22.01.18 add item repository
) : BaseViewModel() {

    // TODO 22.01.18 add State?
    private val _marketData = MutableLiveData<List<TownMarketModel>>()
    val marketData: LiveData<List<TownMarketModel>> = _marketData

    override fun fetchData(): Job = viewModelScope.launch {

        // get list after get location data
        if (LocationData.locationStateLiveData.value is LocationState.Success) {
            // sorted by distance
            _marketData.value = homeRepository.getAllMarketList().sortedBy { it.distance }
        }
    }
}