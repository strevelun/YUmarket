package com.example.YUmarket.widget.adapter.viewholder.homemain

import com.example.YUmarket.databinding.ViewholderNearbyMarketBinding
import com.example.YUmarket.model.homelist.HomeListModel
import com.example.YUmarket.screen.base.BaseViewModel
import com.example.YUmarket.widget.adapter.listener.AdapterListener
import com.example.YUmarket.widget.adapter.listener.home.HomeListListener
import com.example.YUmarket.widget.adapter.viewholder.ModelViewHolder

/**
 * 근처 마켓을 HomeMainFragment에서 RecyclerView로 보여줄때 사용할 ViewHolder
 * @author Main 정남진, Sub 김건우, 김도엽, 배은호, 허희태
 * @since 2021.01.18
 * @param binding Data와 Listener를 Binding시 사용할 ViewBinding
 */
class NearbyMarketViewHolder(
    private val binding: ViewholderNearbyMarketBinding,
    viewModel: BaseViewModel,
    // TODO 22.01.18 add res provider
) : ModelViewHolder<HomeListModel>(binding, viewModel) {

    override fun reset() {
        // TODO 22.01.18 clear image view
    }

    override fun bindViews(model: HomeListModel, listener: AdapterListener) {
        if (listener is HomeListListener) {
            binding.root.setOnClickListener {
                listener.onClickItem(model)
            }
        }
    }

    override fun bindData(model: HomeListModel) {
        super.bindData(model)

        with(binding) {
            nearbyMarketTitle.text = model.title

            // 기본은 closed
            // Open일 경우에만 상태를 변경한다.
            if (model.isMarketOpen) {
                // TODO 22.01.18 change color to yellow
                nearByMarketState.text = "Open"
            }

            nearbyMarketDistance.text = String.format("%.1fkm", model.distance)
            nearbyMarketWorkDay.text = "연중무휴연중무휴연중무휴" // TODO hard coded
            // TODO 22.01.18 load image
        }
    }
}