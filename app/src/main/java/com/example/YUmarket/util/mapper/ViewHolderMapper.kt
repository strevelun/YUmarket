package com.example.YUmarket.util.mapper

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.YUmarket.databinding.ViewholderRestaurantBinding
import com.example.YUmarket.model.CellType
import com.example.YUmarket.model.Model
import com.example.YUmarket.screen.base.BaseViewModel
import com.example.YUmarket.widget.adapter.viewholder.ModelViewHolder
import com.example.YUmarket.widget.adapter.viewholder.restaurant.RestaurantViewHolder

object ViewHolderMapper {
    @Suppress("UNCHECKED_CAST")
    fun <M : Model> map(
        parent: ViewGroup,
        type: CellType,
        viewModel: BaseViewModel,
        // TODO resource provider
    ): ModelViewHolder<M> {

        val inflater = LayoutInflater.from(parent.context)

        return when (type) {
            CellType.RESTAURANT_CELL -> {
                RestaurantViewHolder(
                    ViewholderRestaurantBinding.inflate(inflater),
                    viewModel,
                )
            }

            CellType.HOME_CELL -> {
                RestaurantViewHolder(
                    ViewholderRestaurantBinding.inflate(inflater),
                    viewModel,
                )
            }
        } as ModelViewHolder<M>

    }
}