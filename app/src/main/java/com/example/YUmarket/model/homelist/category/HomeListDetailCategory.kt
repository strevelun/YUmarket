package com.example.YUmarket.model.homelist.category

import androidx.annotation.StringRes
import com.example.YUmarket.R

/**
 * HomeListFragment에 적용되는 세부 카테고리.
 * @author Doyeop Kim (main),
 * Geonwoo Kim, Heetae Heo, Namjin Jeong, Eunho Bae (sub)
 * @since 2022/01/18
 * @param detailCategoryNameId View에서 보여지는 카테고리의 세부 분류
 * @param detailCategoryTypeId 검색 쿼리에 사용이 될 카테고리의 세부 분류
 * @param homeListCategory 해당 카테고리의 대분류
 */
enum class HomeListDetailCategory(
    @StringRes detailCategoryNameId: Int,
    @StringRes detailCategoryTypeId: Int,
    private val homeListCategory: HomeListCategory
) {

    TOWN_MARKET_ALL(R.string.home_detail_all, R.string.home_detail_all_type, HomeListCategory.TOWN_MARKET),
    TOWN_MARKET_FOOD(R.string.food, R.string.food_type, HomeListCategory.TOWN_MARKET),
    TOWN_MARKET_MART(R.string.mart, R.string.mart_type, HomeListCategory.TOWN_MARKET),
    TOWN_MARKET_SERVICE(R.string.service, R.string.service_type, HomeListCategory.TOWN_MARKET),
    TOWN_MARKET_FASHION(R.string.fashion, R.string.fashion_type, HomeListCategory.TOWN_MARKET),
    TOWN_MARKET_ACCESSORY(R.string.accessory, R.string.accessory_type, HomeListCategory.TOWN_MARKET),

    FOOD_ALL(R.string.home_detail_all, R.string.home_detail_all_type, HomeListCategory.FOOD),
    JOKBAL_BOSSAM(R.string.home_detail_jokbal_bossam, R.string.home_detail_jokbal_bossam_type, HomeListCategory.FOOD),
    CUTLET_JAPAN_FOOD(R.string.home_detail_cutlet_japan, R.string.home_detail_cutlet_japan_type, HomeListCategory.FOOD),
    CAFE_BREAD(R.string.home_detail_cafe_bread, R.string.home_detail_cafe_bread_type, HomeListCategory.FOOD),
    DESSERT(R.string.home_detail_dessert, R.string.home_detail_dessert_type, HomeListCategory.FOOD),
    FAST_FOOD(R.string.home_detail_fast_food, R.string.home_detail_fast_food_type, HomeListCategory.FOOD),
    CHINA_FOOD(R.string.home_detail_china_food, R.string.home_detail_china_food_type, HomeListCategory.FOOD),
    PIZZA(R.string.home_detail_pizza, R.string.home_detail_pizza_type, HomeListCategory.FOOD),
    FOOD_EXTRA(R.string.home_detail_extra, R.string.home_detail_extra_type, HomeListCategory.FOOD),

    MART_ALL(R.string.home_detail_all, R.string.home_detail_all_type, HomeListCategory.MART),
    SNACK_AND_BREAD(R.string.home_detail_snack_and_bread, R.string.home_detail_snack_and_bread_type, HomeListCategory.MART),
    BEVERAGE_COFFEE_AND_MILK_PRODUCTS(R.string.home_detail_beverage_coffee_and_milk_product, R.string.home_detail_beverage_coffee_and_milk_product_type, HomeListCategory.MART),
    INSATANT_COOK(R.string.home_detail_instant_cook, R.string.home_detail_instant_cook_type, HomeListCategory.MART),
    WASHING_PRODUCTS(R.string.home_detail_washing_products, R.string.home_detail_washing_products_type, HomeListCategory.MART),
    MART_EXTRA(R.string.home_detail_extra, R.string.home_detail_extra_type, HomeListCategory.MART),

    SERVICE_ALL(R.string.home_detail_all, R.string.home_detail_all_type, HomeListCategory.SERVICE),
    HAIR_SHOP(R.string.home_detail_hair_shop, R.string.home_detail_hair_shop_type, HomeListCategory.SERVICE),
    MASSAGE_AND_SKIN_CARE(R.string.home_detail_massage_and_skin_care, R.string.home_detail_massage_and_skin_care_type, HomeListCategory.SERVICE),
    STUDY_CAFE(R.string.home_detail_study_cafe, R.string.home_detail_study_cafe_type, HomeListCategory.SERVICE),
    SERVICE_EXTRA(R.string.home_detail_extra, R.string.home_detail_extra_type, HomeListCategory.SERVICE),

    FASHION_ALL(R.string.home_detail_all, R.string.home_detail_all_type, HomeListCategory.FASHION),
    MAN_TOP(R.string.home_detail_man_top, R.string.home_detail_man_top_type, HomeListCategory.FASHION),
    MAN_PANTS(R.string.home_detail_man_pants, R.string.home_detail_man_pants_type, HomeListCategory.FASHION),
    WOMAN_TOP(R.string.home_detail_woman_top, R.string.home_detail_woman_top_type, HomeListCategory.FASHION),
    WOMAN_PANTS(R.string.home_detail_woman_pants, R.string.home_detail_woman_pants_type, HomeListCategory.FASHION),
    FASHION_EXTRA(R.string.home_detail_extra, R.string.home_detail_extra_type, HomeListCategory.FASHION),

    ACCESSORY_ALL(R.string.home_detail_all, R.string.home_detail_all_type, HomeListCategory.ACCESSORY),
    SHOES(R.string.home_detail_shoes, R.string.home_detail_shoes_type, HomeListCategory.ACCESSORY),
    MAN_BAG(R.string.home_detail_man_bag, R.string.home_detail_man_bag_type, HomeListCategory.ACCESSORY),
    WOMAN_BAG(R.string.home_detail_woman_bag, R.string.home_detail_woman_bag_type, HomeListCategory.ACCESSORY),
    EARRINGS(R.string.home_detail_earrings, R.string.home_detail_earrings_type, HomeListCategory.ACCESSORY),
    ACCESSORY_EXTRA(R.string.home_detail_extra, R.string.home_detail_extra_type, HomeListCategory.ACCESSORY),

    ETC_ALL(R.string.home_detail_etc_all, R.string.home_detail_etc_all, HomeListCategory.ETC)

}