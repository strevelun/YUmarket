package com.example.YUmarket.widget.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.YUmarket.model.Model
import com.example.YUmarket.screen.base.BaseViewModel
import com.example.YUmarket.widget.adapter.listener.AdapterListener

abstract class ModelViewHolder<M : Model>(
    binding: ViewBinding,
    protected val viewModel: BaseViewModel
) : RecyclerView.ViewHolder(binding.root) {

    abstract fun reset()

    open fun bindData(model: M) {
        reset()
    }

    abstract fun bindViews(model: M, listener: AdapterListener)
}