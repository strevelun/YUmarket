package com.example.YUmarket.screen.home.homelist

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.YUmarket.databinding.FragmentHomeListBinding
import com.example.YUmarket.model.homelist.HomeListModel
import com.example.YUmarket.screen.base.BaseFragment
import com.example.YUmarket.widget.adapter.ModelRecyclerAdapter
import com.example.YUmarket.widget.adapter.listener.home.HomeListListener
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class HomeListFragment : BaseFragment<HomeListViewModel, FragmentHomeListBinding>() {
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

        // TODO delete
        testView.text = context?.getText(homeCategory.categoryNameId)
    }

    private fun showMessage(message: String) =
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()



    override fun observeData() = with(viewModel) {
        homeListData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    companion object {
        const val HOME_CATEGORY_KEY = "HomeCategoryKey"

        fun newInstance(homeCategory: HomeCategory) : HomeListFragment {
            val bundle = Bundle().apply {
                putSerializable(HOME_CATEGORY_KEY, homeCategory)
            }

            return HomeListFragment().apply {
                arguments = bundle
            }
        }
    }
}