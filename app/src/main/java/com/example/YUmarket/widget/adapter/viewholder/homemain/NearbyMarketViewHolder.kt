package com.example.YUmarket.widget.adapter.viewholder.homemain

import com.example.YUmarket.R
import com.example.YUmarket.databinding.ViewholderNearbyMarketBinding
import com.example.YUmarket.extensions.clear
import com.example.YUmarket.extensions.load
import com.example.YUmarket.model.homelist.TownMarketModel
import com.example.YUmarket.screen.base.BaseViewModel
import com.example.YUmarket.util.provider.ResourcesProvider
import com.example.YUmarket.widget.adapter.listener.AdapterListener
import com.example.YUmarket.widget.adapter.listener.home.TownMarketListener
import com.example.YUmarket.widget.adapter.viewholder.ModelViewHolder

/**
 * 근처 마켓을 HomeMainFragment에서 RecyclerView로 보여줄때 사용할 ViewHolder
 * @author Main 정남진, Sub 김건우, 김도엽, 배은호, 허희태
 * @since 2021.01.18
 * @param binding Data와 Listener를 Binding시 사용할 ViewBinding
 * @param resourcesProvider Resource를 가져올 때 사용할 ResourcesProvider
 */
class NearbyMarketViewHolder(
    private val binding: ViewholderNearbyMarketBinding,
    viewModel: BaseViewModel,
    resourcesProvider: ResourcesProvider
) : ModelViewHolder<TownMarketModel>(binding, viewModel, resourcesProvider) {

    override fun reset() {
        // Extension을 이용하여 ImageView 초기화
        binding.nearbyMarketImageView.clear()
    }

    override fun bindViews(model: TownMarketModel, listener: AdapterListener) {
        if (listener is TownMarketListener) {
            binding.root.setOnClickListener {
                listener.onClickItem(model)
            }
        }
    }

    override fun bindData(model: TownMarketModel) {
        super.bindData(model)

        with(binding) {
            nearbyMarketTitle.text = model.marketName

            // 기본은 closed
            // Open일 경우에만 상태를 변경한다.
            if (model.isMarketOpen) {
                nearByMarketState.text = resourcesProvider.getString(R.string.market_open)
                nearByMarketState.setChipBackgroundColorResource(R.color.yellow)
            }

            nearbyMarketDistance.text = String.format("%.1fkm", model.distance)
            nearbyMarketWorkDay.text = "연중무휴연중무휴연중무휴" // TODO remove hard coded
            
            // Extension을 사용하여 Glide로 ImageView에 이미지 로드
            nearbyMarketImageView.load(model.marketImageUrl)
        }
    }
}