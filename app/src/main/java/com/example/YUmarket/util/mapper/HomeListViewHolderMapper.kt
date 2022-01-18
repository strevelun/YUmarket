package com.example.YUmarket.util.mapper

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.YUmarket.databinding.ViewholderHomeItemBinding
import com.example.YUmarket.databinding.ViewholderTownMarketBinding
import com.example.YUmarket.model.homelist.HomeListModel
import com.example.YUmarket.model.homelist.category.HomeListCategory
import com.example.YUmarket.screen.base.BaseViewModel
import com.example.YUmarket.util.provider.ResourcesProvider
import com.example.YUmarket.widget.adapter.viewholder.home.HomeItemModelViewHolder
import com.example.YUmarket.widget.adapter.viewholder.home.HomeModelViewHolder
import com.example.YUmarket.widget.adapter.viewholder.home.TownMarketViewHolder

/**
 * HomeListViewHolder를 homeListCategory에 따라 분기하여 리턴해주는 mapper class
 * @author Doyeop Kim (main),
 * Geonwoo Kim, Heetae Heo, Namjin Jeong, Eunho Bae (sub)
 * @since 2022/01/18
 */
object HomeListViewHolderMapper {

    @SuppressLint("UNCHECKED_CAST")
    fun <M: HomeListModel> map(
        parent: ViewGroup,
        category: HomeListCategory,
        viewModel: BaseViewModel,
        resourcesProvider: ResourcesProvider
    ): HomeModelViewHolder<M> {
        val inflater = LayoutInflater.from(parent.context)

        val viewHolder = when(category) {
            HomeListCategory.TOWN_MARKET -> TownMarketViewHolder(
                ViewholderTownMarketBinding.inflate(inflater),
                viewModel,
                resourcesProvider
            )

            else -> HomeItemModelViewHolder(
                ViewholderHomeItemBinding.inflate(inflater),
                viewModel,
                resourcesProvider
            )
        }

        return viewHolder as HomeModelViewHolder<M>
    }
}