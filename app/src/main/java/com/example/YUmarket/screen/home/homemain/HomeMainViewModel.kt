package com.example.YUmarket.screen.home.homemain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.YUmarket.data.repository.restaurant.HomeRepository
import com.example.YUmarket.model.CellType
import com.example.YUmarket.model.homelist.HomeItemModel
import com.example.YUmarket.model.homelist.category.HomeListCategory
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

    private val _marketData = MutableLiveData<HomeMainState>(HomeMainState.Uninitialized)
    val marketData: LiveData<HomeMainState> = _marketData

    private val _itemData = MutableLiveData<HomeMainState>(HomeMainState.Uninitialized)
    val itemData: LiveData<HomeMainState> = _itemData

    private lateinit var allNewSaleItemsList: List<HomeItemModel>

    override fun fetchData(): Job = viewModelScope.launch {
        // get list after get location data
        if (LocationData.locationStateLiveData.value is LocationState.Success) {
            fetchMarketData()
            fetchItemData()
        }
    }

    private suspend fun fetchMarketData() {
        // 22.01.19 성공적으로 불러온 뒤에는 reloadData 이외에 Data는 계속 불러오는 것을 방지
        // by 정남진
        if (marketData.value !is HomeMainState.Success<*>
        ) {
            _marketData.value = HomeMainState.Loading

            // sorted by distance
            _marketData.value = HomeMainState.Success(
                // 임시로 CellType을 ViewModel에서 변경
                modelList = homeRepository.getAllMarketList().map {
                    it.copy(type = CellType.HOME_CELL)
                }.sortedBy { it.distance }
            )
        }
    }

    private suspend fun fetchItemData() {
        if (itemData.value !is HomeMainState.Success<*>
        ) {
            _itemData.value = HomeMainState.Loading

            allNewSaleItemsList = homeRepository.getAllNewSaleItems()
            _itemData.value = HomeMainState.ListLoaded
        }
    }

    fun reloadData(): Job {
        _marketData.value = HomeMainState.Loading
        _itemData.value = HomeMainState.Loading
        return fetchData()
    }

    fun setItemFilter(category: HomeListCategory) {
        if (::allNewSaleItemsList.isInitialized) {
            _itemData.value = HomeMainState.Success(
                // 임시로 CellType을 ViewModel에서 변경
                modelList = allNewSaleItemsList.filter {
                    it.homeListCategory == category
                }
            )
        }
    }
}