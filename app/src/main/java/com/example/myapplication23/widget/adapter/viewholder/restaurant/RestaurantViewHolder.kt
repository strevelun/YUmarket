package com.example.myapplication23.widget.adapter.viewholder.restaurant

import android.util.Log
import androidx.viewbinding.ViewBinding
import com.example.YUmarket.util.provider.ResourcesProvider
import com.example.myapplication23.databinding.ViewholderRestaurantBinding
import com.example.myapplication23.model.restaurant.RestaurantModel
import com.example.myapplication23.screen.base.BaseViewModel
import com.example.myapplication23.widget.adapter.listener.AdapterListener
import com.example.myapplication23.widget.adapter.listener.restaurant.RestaurantListListener
import com.example.myapplication23.widget.adapter.viewholder.ModelViewHolder

class RestaurantViewHolder(
    private val binding: ViewholderRestaurantBinding,
    viewModel: BaseViewModel,
    resourcesProvider: ResourcesProvider
) : ModelViewHolder<RestaurantModel>(binding, viewModel, resourcesProvider) {
    override fun reset() = Unit

    override fun bindData(model: RestaurantModel) {
        super.bindData(model)
        binding.restaurantTitle.text = model.title
        Log.d("TAG", "bindData: $model")
    }

    override fun bindViews(model: RestaurantModel, adapterListener: AdapterListener) {
        if (adapterListener is RestaurantListListener) {
            binding.root.setOnClickListener {
                adapterListener.onClickItem(model)
            }
        }
    }
}