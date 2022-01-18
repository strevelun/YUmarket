package com.example.YUmarket.screen.home


import androidx.core.view.isVisible
import com.example.YUmarket.databinding.FragmentHomeBinding
import com.example.YUmarket.model.homelist.category.HomeListCategory
import com.example.YUmarket.screen.base.BaseFragment
import com.example.YUmarket.screen.home.homelist.HomeListFragment
import com.example.YUmarket.util.LocationData
import com.example.YUmarket.util.LocationState
import com.example.YUmarket.widget.adapter.HomeListFragmentPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment
    : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    private lateinit var viewPagerAdapter: HomeListFragmentPagerAdapter

    override val viewModel by viewModel<HomeViewModel>()

    override fun getViewBinding(): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater)

    override fun initViews() {
        super.initViews()
    }

    override fun observeData() = with(binding) {
        LocationData.locationStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is LocationState.Success -> {
                    initViewPager()
                }
            }
        }
    }

    private fun initViewPager() = with(binding) {
        orderChipGroup.isVisible = true

        if (::viewPagerAdapter.isInitialized.not()) {
            val homeListCategories = HomeListCategory.values()

            val homeListFragmentList = homeListCategories.map {
//                RestaurantListFragment.newInstance(it, locationLatLng)
                HomeListFragment.newInstance(it)
            }
            viewPagerAdapter = HomeListFragmentPagerAdapter(
                this@HomeFragment,
                homeListFragmentList,
//                locationLatLng
            )
            viewPager.adapter = viewPagerAdapter

            viewPager.offscreenPageLimit = homeListCategories.size

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.setText(homeListCategories[position].categoryNameId)
            }.attach()
        }


//        if (locationLatLng != viewPagerAdapter.locationLatLng) {
//            viewPagerAdapter.locationLatLng = locationLatLng
//            viewPagerAdapter.fragmentList.forEach {
//                it.viewModel.setLocationLatLng(locationLatLng)
//            }
//        }
    }


    companion object {
        const val TAG = "HomeFragment"

        fun newInstance() : HomeFragment {
            return HomeFragment().apply {

            }
        }
    }
}