package com.example.YUmarket.widget.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.YUmarket.model.homelist.HomeListModel
import com.example.YUmarket.model.homelist.category.HomeListCategory
import com.example.YUmarket.screen.base.BaseViewModel
import com.example.YUmarket.util.mapper.HomeListViewHolderMapper
import com.example.YUmarket.util.provider.ResourcesProvider
import com.example.YUmarket.widget.adapter.listener.AdapterListener
import com.example.YUmarket.widget.adapter.viewholder.home.HomeModelViewHolder

/**
 * HomeFragment의 recyclerView에 적용이 되는 RecyclerAdapter class (it's bout HomeItemModel)
 * @author Doyeop Kim (main),
 * Geonwoo Kim, Heetae Heo, Namjin Jeong, Eunho Bae (sub)
 * @since 2022/01/18
 */
class HomeModelRecyclerAdapter<M : HomeListModel, VM : BaseViewModel>(
    private var modelList: List<HomeListModel>,
    private val viewModel: VM,
    private val resourcesProvider: ResourcesProvider,
    private val adapterListener: AdapterListener
) : ListAdapter<HomeListModel, HomeModelViewHolder<HomeListModel>>(HomeListModel.DIFF_CALLBACK) {

    override fun getItemCount(): Int = modelList.size

    override fun getItemViewType(position: Int): Int = modelList[position].homeListCategory.ordinal

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeModelViewHolder<HomeListModel> = HomeListViewHolderMapper.map(
        parent,
        HomeListCategory.values()[viewType],
        viewModel,
        resourcesProvider
    )

    override fun onBindViewHolder(holder: HomeModelViewHolder<HomeListModel>, position: Int) {
        holder.bindData(modelList[position] as M)
        holder.bindViews(modelList[position] as M, adapterListener)
    }

    override fun submitList(list: List<HomeListModel>?) {
        list?.let {
            modelList = it
        }

        super.submitList(list)
    }
}