package com.example.YUmarket.model.homelist.category

import androidx.annotation.StringRes
import com.example.YUmarket.R

/**
 * HomeItem들의 대분류를 정의한 enum class
 * @author Doyeop Kim (main)
 * Geonwoo Kim, Heetae Heo, Namjin Jeong, Eunho Bae (sub)
 * @since 2022/01/18
 * @param categoryNameId View에서 보여지는 카테고리의 이름
 * @param categoryTypeId 검색 쿼리에 활용이 되는 카테고리의 이름
 */
enum class HomeListCategory(
    @StringRes val categoryNameId: Int,
    @StringRes val categoryTypeId: Int
) {
    TOWN_MARKET(R.string.all, R.string.all_type),
    FOOD(R.string.food, R.string.food_type),
    MART(R.string.mart, R.string.mart_type),
    SERVICE(R.string.service, R.string.service_type),
    FASHION(R.string.fashion, R.string.fashion_type),
    ACCESSORY(R.string.accessory, R.string.accessory_type),
    ETC(R.string.etc, R.string.etc_type)
}