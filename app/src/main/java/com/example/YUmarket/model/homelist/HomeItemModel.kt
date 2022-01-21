package com.example.YUmarket.model.homelist

import com.example.YUmarket.model.CellType
import com.example.YUmarket.model.Model
import com.example.YUmarket.model.homelist.category.HomeListCategory
import com.example.YUmarket.model.homelist.category.HomeListDetailCategory

/**
 * TownMarket을 제외한 HomeListFragment의 아이템을 담는 Model data class이다. 서버 단계에서 찜과 리뷰의 개수를 바로 반환해주는 식으로 적용해야함.
 * @author Doyeop Kim (main),
 * Geonwoo Kim, Heetae Heo, Namjin Jeong, Eunho Bae (sub)
 * @since 2022/01/18
 * @param townMarketModel HomeItemModel과 TownMarketModel과는 서로 연관관계가 존재해야함 (1 : N)
 */
// TODO repository를 통해서 entity -> model 매핑 시 연관관계를 반영해주기
data class HomeItemModel(
    override val id: Long,
    val homeListCategory: HomeListCategory,
    val homeListDetailCategory: HomeListDetailCategory,
    val itemImageUrl: String,
    val townMarketModel: TownMarketModel,
    val itemName: String,
    val originalPrice: Int,
    val salePrice: Int,
    val stockQuantity: Int,
    val likeQuantity: Int,
    val reviewQuantity: Int,
    override val type: CellType = CellType.HOME_ITEM_CELL
): Model(id, type) {

    /**
     * [Model.isTheSame]을 Override
     */
    override fun isTheSame(item: Model) =
        if (item is HomeItemModel) {
            super.isTheSame(item) && this.homeListCategory == item.homeListCategory
        } else {
            false
        }
}