package com.example.YUmarket.widget.adapter.viewholder.home

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.YUmarket.model.homelist.HomeListModel
import com.example.YUmarket.screen.base.BaseViewModel
import com.example.YUmarket.widget.adapter.listener.AdapterListener

/**
 * HomeListFragment의 recyclerView에 적용이 되는 viewHolder를 정의한 abstract class
 * @author Doyeop Kim (main),
 * Geonwoo Kim, Heetae Heo, Namjin Jeong, Eunho Bae (sub)
 * @since 2022/01/18
 */
abstract class HomeModelViewHolder<M: HomeListModel>(
    binding: ViewBinding,
    protected val viewModel: BaseViewModel
): RecyclerView.ViewHolder(binding.root) {

    abstract fun reset()

    // 해당 view에 데이터를 bind하는 기능을 수행하는 메소드
    open fun bindData(model: M) {
        reset()
    }

    // 해당 view에 대한 속성을 정의하는 기능을 수행하는 메소드
    abstract fun bindViews(model: M, adapterListener: AdapterListener)
}