package com.example.YUmarket.screen.home.homelist

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.YUmarket.databinding.FragmentHomeListBinding
import com.example.YUmarket.model.homelist.HomeItemModel
import com.example.YUmarket.model.homelist.HomeListModel
import com.example.YUmarket.model.homelist.TownMarketModel
import com.example.YUmarket.model.homelist.category.HomeListCategory
import com.example.YUmarket.screen.base.BaseFragment
import com.example.YUmarket.util.provider.ResourcesProvider
import com.example.YUmarket.widget.adapter.HomeModelRecyclerAdapter
import com.example.YUmarket.widget.adapter.ModelRecyclerAdapter
import com.example.YUmarket.widget.adapter.listener.home.HomeItemListener
import com.example.YUmarket.widget.adapter.listener.home.TownMarketListener
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/**
 * HomeFragment의 viewPager에 들어가는 fragment를 정의한 class (view)
 * @author Doyeop Kim (main),
 * Geonwoo Kim, Heetae Heo, Namjin Jeong, Eunho Bae (sub)
 * @since 2022/01/18
 */
class HomeListFragment : BaseFragment<HomeListViewModel, FragmentHomeListBinding>() {

    private val resourcesProvider by inject<ResourcesProvider>()

    override fun getViewBinding(): FragmentHomeListBinding =
        FragmentHomeListBinding.inflate(layoutInflater)

    private val homeListCategory: HomeListCategory by lazy {
        arguments?.getSerializable(HOME_CATEGORY_KEY) as HomeListCategory
    }

    override val viewModel by viewModel<HomeListViewModel> {
        parametersOf(homeListCategory)
    }

    private val adapter by lazy {
        HomeModelRecyclerAdapter<HomeListModel, HomeListViewModel>(
            listOf(), viewModel, resourcesProvider,
            adapterListener = when (homeListCategory) {
                HomeListCategory.TOWN_MARKET -> {
                    object : TownMarketListener {
                        override fun onClickItem(townMarketModel: TownMarketModel) = Unit
                    }
                }

                else -> {
                    object : HomeItemListener {
                        override fun onClickItem(item: HomeItemModel) {
                            // TODO startActivity
                            when(homeListCategory) {
                                HomeListCategory.FOOD -> Toast.makeText(requireContext(), "Food!", Toast.LENGTH_SHORT).show()
                                HomeListCategory.MART -> Toast.makeText(requireContext(), "Mart!", Toast.LENGTH_SHORT).show()
                                HomeListCategory.SERVICE -> Toast.makeText(requireContext(), "Service!", Toast.LENGTH_SHORT).show()
                                HomeListCategory.FASHION-> Toast.makeText(requireContext(), "Fashion!", Toast.LENGTH_SHORT).show()
                                HomeListCategory.ACCESSORY -> Toast.makeText(requireContext(), "Accessory!", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        )
    }

    override fun initViews() = with(binding) {
        restaurantRecyclerView.adapter = adapter
        restaurantRecyclerView.layoutManager = LinearLayoutManager(this@HomeListFragment.context)
    }

    override fun observeData() = with(viewModel) {
        homeListLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    companion object {
        const val HOME_CATEGORY_KEY = "HomeCategoryKey"

        fun newInstance(homeListCategory: HomeListCategory) : HomeListFragment {
            val bundle = Bundle().apply {
                putSerializable(HOME_CATEGORY_KEY, homeListCategory)
            }

            return HomeListFragment().apply {
                arguments = bundle
            }
        }
    }
}