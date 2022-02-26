package com.example.myapplication23.widget.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.YUmarket.extensions.load
import com.example.myapplication23.R
import com.example.myapplication23.databinding.ViewholderMapViewpagerBinding
import com.example.myapplication23.model.map.MapItemModel
import com.example.myapplication23.model.map.MapStoreModel
import com.example.myapplication23.widget.adapter.listener.AdapterListener
import com.example.myapplication23.widget.adapter.listener.map.MapItemListAdapterListener
import com.example.myapplication23.widget.adapter.listener.restaurant.RestaurantListListener
import kotlinx.android.extensions.LayoutContainer
import kotlin.reflect.KMutableProperty0

class MapFragmentPagerAdapter(
    private val fragment : Fragment,
    private val adapterListener: MapItemListAdapterListener
) :
    RecyclerView.Adapter<MapFragmentPagerAdapter.ItemViewHolder>() {

    private var items: ArrayList<MapItemModel> = arrayListOf()
    private lateinit var store: MapStoreModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // val holder = LayoutInflater.from(parent.context)
        //    .inflate(R.layout.viewholder_map_viewpager, parent, false)


        val binding = ViewholderMapViewpagerBinding.inflate(fragment.layoutInflater, parent, false)

        return ItemViewHolder(binding)
    }

    // 지점이 공백이면 본점이라고 표시
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        holder.bind(items[position])
        holder.bindViews(items[position], adapterListener)

    }

    override fun getItemCount(): Int = items.size

    fun registerStore(store: MapStoreModel) {
        this.store = store
        items = store.items
    }

    //override val containerView: View
    inner class ItemViewHolder(private val binding: ViewholderMapViewpagerBinding) :
        RecyclerView.ViewHolder(binding.root)
        //LayoutContainer
    {


        fun bind(item: MapItemModel) {
            with(binding) {
                ivViewpagerProfile.load(item.itemImageUrl)
                tvViewpagerTitle.text = item.itemName
                tvViewpagerBranch.text = if (store.branch.isNullOrEmpty()) "본점" else store.branch
                tvViewpagerPrice.text = item.originalPrice.toString() + "원"
                tvViewpagerDiscountRate.text = item.discountRate.toString() + "%"
                tvViewpagerDiscountedPrice.text =
                    Math.round((item.originalPrice * (100.0f - item.discountRate) * 0.01f))
                        .toString() + "원"
                tvViewpagerPage.text = "${position + 1} / ${itemCount}"
            }
        }

        fun bindViews(item : MapItemModel, adapterListener : MapItemListAdapterListener){
            binding.root.setOnClickListener { adapterListener.onClickItem(item) }
        }
    }


    inner class ZoomOutPageTransformer : ViewPager2.PageTransformer {

        private val MIN_SCALE = 0.85f // 뷰가 몇퍼센트로 줄어들 것인지
        private val MIN_ALPHA = 0.5f // 어두워지는 정도를 나타낸 듯 하다.

        override fun transformPage(view: View, position: Float) {
            view.apply {
                val pageWidth = width
                val pageHeight = height
                when {
                    position < -1 -> { // [-Infinity,-1)
                        // This page is way off-screen to the left.
                        alpha = 0f
                    }
                    position <= 1 -> { // [-1,1]
                        // Modify the default slide transition to shrink the page as well
                        val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))
                        val vertMargin = pageHeight * (1 - scaleFactor) / 2
                        val horzMargin = pageWidth * (1 - scaleFactor) / 2
                        translationX = if (position < 0) {
                            horzMargin - vertMargin / 2
                        } else {
                            horzMargin + vertMargin / 2
                        }

                        // Scale the page down (between MIN_SCALE and 1)
                        scaleX = scaleFactor
                        scaleY = scaleFactor

                        // Fade the page relative to its size.
                        alpha = (MIN_ALPHA +
                                (((scaleFactor - MIN_SCALE) / (1 - MIN_SCALE)) * (1 - MIN_ALPHA)))
                    }
                    else -> { // (1,+Infinity]
                        // This page is way off-screen to the right.
                        alpha = 0f
                    }
                }
            }
        }
    }
}