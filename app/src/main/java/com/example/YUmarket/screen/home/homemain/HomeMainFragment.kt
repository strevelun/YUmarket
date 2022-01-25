package com.example.YUmarket.screen.home.homemain

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.YUmarket.R
import com.example.YUmarket.databinding.FragmentHomeMainBinding
import com.example.YUmarket.model.homelist.TownMarketModel
import com.example.YUmarket.model.homelist.category.HomeListCategory
import com.example.YUmarket.screen.base.BaseFragment
import com.example.YUmarket.util.LocationData
import com.example.YUmarket.util.LocationState
import com.example.YUmarket.util.provider.ResourcesProvider
import com.example.YUmarket.widget.adapter.ModelRecyclerAdapter
import com.example.YUmarket.widget.adapter.listener.home.TownMarketListener
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


/**
 * BottomNavigationView에서 Home을 선택하면 띄워줄 Fragment
 * @author Main 정남진, Sub 김건우, 김도엽, 배은호, 허희태
 * @since 2022.01.18
 */
class HomeMainFragment
    : BaseFragment<HomeMainViewModel, FragmentHomeMainBinding>(),
    AdapterView.OnItemSelectedListener {

    override val viewModel by viewModel<HomeMainViewModel>()

    private val resourcesProvider by inject<ResourcesProvider>()

    override fun getViewBinding(): FragmentHomeMainBinding =
        FragmentHomeMainBinding.inflate(layoutInflater)

    // Spinner에 사용될 HomeListCategory List
    // drop(1)을 하여 동네마켓 항목은 제외
    private val categories = HomeListCategory.values().drop(1)

    override fun observeData() = with (viewModel) {
        // marketData가 변경되면 update
        viewModel.marketData.observe(viewLifecycleOwner) {
            when (it) {
                // TODO 22.01.19 add more state handle logics

                is HomeMainState.Uninitialized -> {

                }

                is HomeMainState.Loading -> {

                }

                is HomeMainState.Success -> {
                    nearbyMarketAdapter.submitList(it.marketModelList)
                }

                is HomeMainState.Error -> {
                    Toast.makeText(
                        context,
                        R.string.cannot_load_data,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        // 위치 정보를 불러오고 fetchData
        LocationData.locationStateLiveData.observe(viewLifecycleOwner) {
            // get list after get location
            if (it is LocationState.Success) {
                viewModel.fetchData()
            }
        }
    }

    private val nearbyMarketAdapter by lazy {
        ModelRecyclerAdapter<TownMarketModel, HomeMainViewModel>(
            listOf(),
            viewModel,
            resourcesProvider,
            object : TownMarketListener {
                // RecyclerView의 Item을 클릭할때
                override fun onClickItem(model: TownMarketModel) {
                    // TODO 22.01.18 start detail market activity when clicked
                    Toast.makeText(context, model.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        )
    }

    override fun initViews() {

        super.initViews()

        // Spinner의 Adapter에 사용할 List
        // 마켓의 업종을 나타내는 String
        val adapterList = categories.map {
            getString(it.categoryNameId)
        }

        with(binding) {
            // 새로운 할인상품에 사용할 Spinner의 Adapter 설정
            newSaleItemSpinner.adapter = ArrayAdapter(
                requireContext(),
                R.layout.support_simple_spinner_dropdown_item,
                adapterList
            )

            // 현재 Fragment가 AdapterView.OnItemSelectedListener를 상속받아
            // Item이 선택됐을때 무엇을 할지 정의함
            newSaleItemSpinner.onItemSelectedListener = this@HomeMainFragment

            // 근처 마켓 RecyclerView 설정
            nearbyMarketRecyclerView.adapter = nearbyMarketAdapter
            
            // 한줄에 2개씩 띄우도록 설정(spanCount)
            nearbyMarketRecyclerView.layoutManager = GridLayoutManager(
                requireContext(),
                2,
                GridLayoutManager.VERTICAL,
                false
            )

            // 더보기를 누르면 마켓을 List로 띄워주는 Fragment로 이동
            showMoreTextView.setOnClickListener {
                findNavController().navigate(
                    HomeMainFragmentDirections.actionHomeMainFragmentToHomeFragment()
                )
            }

            setCategoryButtonListener()
        }
    }

    /**
     * 카테고리 버튼별 동작을 Navigation을 이용하여 설정
     */
    private fun setCategoryButtonListener() = with(binding) {

        foodCategoryListButton.setOnClickListener {
            findNavController().navigate(
                HomeMainFragmentDirections.actionHomeMainFragmentToHomeFragment(HomeListCategory.FOOD)
            )
        }

        martCategoryListButton.setOnClickListener {
            findNavController().navigate(
                HomeMainFragmentDirections.actionHomeMainFragmentToHomeFragment(HomeListCategory.MART)
            )
        }

        serviceCategoryListButton.setOnClickListener {
            findNavController().navigate(
                HomeMainFragmentDirections.actionHomeMainFragmentToHomeFragment(HomeListCategory.SERVICE)
            )
        }

        fashionCategoryListButton.setOnClickListener {
            findNavController().navigate(
                HomeMainFragmentDirections.actionHomeMainFragmentToHomeFragment(HomeListCategory.FASHION)
            )
        }

        accessoryCategoryListButton.setOnClickListener {
            findNavController().navigate(
                HomeMainFragmentDirections.actionHomeMainFragmentToHomeFragment(HomeListCategory.ACCESSORY)
            )
        }

        etcCategoryListButton.setOnClickListener {
            findNavController().navigate(
                HomeMainFragmentDirections.actionHomeMainFragmentToHomeFragment(HomeListCategory.ETC)
            )
        }

    }

    /**
     * Spinner에서 Item을 선택할때 동작 설정
     */
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        viewModel.setItemFilter(categories[position])
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }


    companion object {
        const val TAG = "HomeMainFragment"

        fun newInstance() = HomeMainFragment()
    }
}