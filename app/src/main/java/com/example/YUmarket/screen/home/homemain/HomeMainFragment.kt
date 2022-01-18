package com.example.YUmarket.screen.home.homemain

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.YUmarket.R
import com.example.YUmarket.databinding.FragmentHomeMainBinding
import com.example.YUmarket.model.homelist.HomeListModel
import com.example.YUmarket.model.homelist.category.HomeListCategory
import com.example.YUmarket.screen.base.BaseFragment
import com.example.YUmarket.screen.home.HomeFragment
import com.example.YUmarket.util.LocationData
import com.example.YUmarket.util.LocationState
import com.example.YUmarket.widget.adapter.ModelRecyclerAdapter
import com.example.YUmarket.widget.adapter.listener.home.HomeListListener
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


    override fun getViewBinding(): FragmentHomeMainBinding =
        FragmentHomeMainBinding.inflate(layoutInflater)

    override fun observeData() {
        // marketData가 변경되면 update
        viewModel.marketData.observe(viewLifecycleOwner) {
            nearbyMarketAdapter.submitList(it)
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
        ModelRecyclerAdapter<HomeListModel, HomeMainViewModel>(
            listOf(),
            viewModel,
//            resourcesProvider,
            object : HomeListListener {
                // RecyclerView의 Item을 클릭할때
                override fun onClickItem(model: HomeListModel) {
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
        val adapterList = HomeListCategory.values().map {
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
                showListFragment()
            }

        }
    }


    /**
     * 더보기를 클릭하면 마켓을 RecyclerView로
     * 띄워주는 Fragment(현재 HomeFragment)를 띄워준다.
     * [com.example.YUmarket.screen.MainActivity.showFragment]와 같다.
     */
    private fun showListFragment() {
        val fragmentManager = parentFragmentManager
        val fragmentFound = fragmentManager.findFragmentByTag(HomeFragment.TAG)

        fragmentManager.fragments.forEach {
            fragmentManager.beginTransaction().hide(it).commitAllowingStateLoss()
        }

        fragmentFound?.let {
            fragmentManager.beginTransaction().show(it).commitAllowingStateLoss()
        } ?: kotlin.run {
            fragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, HomeFragment.newInstance(), HomeFragment.TAG)
                .commitAllowingStateLoss()
        }
    }

    /**
     * Spinner에서 Item을 선택할때 동작 설정
     */
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        // TODO 22.01.18 add list sort
        when (position) {
            // 동네마켓
            0 -> {

            }

            // 식/음료
            1 -> {

            }

            // 편의점/마트
            2 -> {

            }

            // 서비스업종
            3 -> {

            }

            // 패션의류
            4 -> {

            }

            // 패션잡화
            5 -> {

            }

        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }


    companion object {
        const val TAG = "HomeMainFragment"

        fun newInstance() = HomeMainFragment()
    }
}