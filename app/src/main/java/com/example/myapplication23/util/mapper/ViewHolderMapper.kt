package com.example.myapplication23.util.mapper

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.YUmarket.util.provider.ResourcesProvider
import com.example.myapplication23.databinding.ViewholderRestaurantBinding
import com.example.myapplication23.model.CellType
import com.example.myapplication23.model.Model
import com.example.myapplication23.screen.base.BaseViewModel
import com.example.myapplication23.widget.adapter.viewholder.ModelViewHolder
import com.example.myapplication23.widget.adapter.viewholder.restaurant.RestaurantViewHolder

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
            CellType.RESTAURANT_CELL -> {
                RestaurantViewHolder(
                    ViewholderRestaurantBinding.inflate(inflater),
                    viewModel,
                    resourcesProvider
                )

            }
            else -> {}
        } as ModelViewHolder<M>
    }
}