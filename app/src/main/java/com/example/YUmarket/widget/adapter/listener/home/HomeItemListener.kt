package com.example.YUmarket.widget.adapter.listener.home

import com.example.YUmarket.model.homelist.HomeItemModel
import com.example.YUmarket.widget.adapter.listener.AdapterListener

/**
 * HomeListFragment의 아이템을 클릭 시 발생하는 이벤트 메소드를 정의한 listener interface
 * @author Doyeop Kim (main),
 * Geonwoo Kim, Heetae Heo, Namjin Jeong, Eunho Bae (sub)
 * @since 2022/01/18
 */
interface HomeItemListener : AdapterListener {

    fun onClickItem(model: HomeItemModel)

}