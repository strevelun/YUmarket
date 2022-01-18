package com.example.YUmarket.model.homelist

import com.example.YUmarket.data.entity.location.LocationLatLngEntity
import com.example.YUmarket.model.CellType
import com.example.YUmarket.model.homelist.category.HomeListCategory

/**
 * 동네마켓 아이템을 정의해주는 Data Class
 * @author Doyeop Kim (main),
 * Geonwoo Kim, Heetae Heo, Namjin Jeong, Eunho Bae (sub)
 * @since 2022/01/18
 * @param locationLatLngEntity 해당 동네마켓의 좌표를 담는 변수
 */
data class TownMarketModel(
    override val id: Long,
    val marketName: String,
    val isMarketOpen: Boolean,
    val locationLatLngEntity: LocationLatLngEntity,
    val marketImageUrl: String,
    override val type: CellType = CellType.HOME_CELL,
    override val homeListCategory: HomeListCategory = HomeListCategory.TOWN_MARKET
) : HomeListModel(id, type, homeListCategory)
