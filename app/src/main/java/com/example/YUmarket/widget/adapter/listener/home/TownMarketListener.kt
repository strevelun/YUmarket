package com.example.YUmarket.widget.adapter.listener.home

import com.example.YUmarket.model.homelist.TownMarketModel
import com.example.YUmarket.widget.adapter.listener.AdapterListener

/**
 * HomeListFragment의 동네마켓 아이템을 클릭 시 발생하는 이벤트 메소드를 모아둔 listener interface
 * @author Doyeop Kim (main),
 * Geonwoo Kim, Heetae Heo, Namjin Jeong, Eunho Bae (sub)
 * @since 2022/01/18
 */
interface TownMarketListener : AdapterListener{

    fun onClickItem(model: TownMarketModel)

}