package com.example.YUmarket.util.mapper

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.YUmarket.databinding.ViewholderHomeItemBinding
import com.example.YUmarket.databinding.ViewholderNearbyMarketBinding
import com.example.YUmarket.databinding.ViewholderTownMarketBinding
import com.example.YUmarket.model.CellType
import com.example.YUmarket.model.Model
import com.example.YUmarket.model.homelist.HomeListModel
import com.example.YUmarket.model.homelist.category.HomeListCategory
import com.example.YUmarket.screen.base.BaseViewModel
import com.example.YUmarket.util.provider.ResourcesProvider
import com.example.YUmarket.widget.adapter.viewholder.ModelViewHolder
import com.example.YUmarket.widget.adapter.viewholder.home.HomeItemModelViewHolder
import com.example.YUmarket.widget.adapter.viewholder.home.HomeModelViewHolder
import com.example.YUmarket.widget.adapter.viewholder.home.TownMarketViewHolder
import com.example.YUmarket.widget.adapter.viewholder.homemain.NearbyMarketViewHolder

object ViewHolderMapper {
    @Suppress("UNCHECKED_CAST")
    fun <M : Model> map(
        parent: ViewGroup,
        type: CellType,
        viewModel: BaseViewModel,
        resourcesProvider: ResourcesProvider
    ): ModelViewHolder<M> {

        val inflater = LayoutInflater.from(parent.context)

        return when (type) {
            CellType.HOME_CELL -> {
                NearbyMarketViewHolder(
                    ViewholderNearbyMarketBinding.inflate(inflater),
                    viewModel,
                    resourcesProvider
                )
            }
        } as ModelViewHolder<M>

    }

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