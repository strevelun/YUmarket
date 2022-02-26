package com.example.myapplication23.screen.home.restaurant

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.YUmarket.util.provider.ResourcesProvider
import com.example.myapplication23.databinding.FragmentRestaurantListBinding
import com.example.myapplication23.model.restaurant.RestaurantModel
import com.example.myapplication23.screen.base.BaseFragment
import com.example.myapplication23.widget.adapter.ModelRecyclerAdapter
import com.example.myapplication23.widget.adapter.listener.restaurant.RestaurantListListener
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class RestaurantListFragment : BaseFragment<RestaurantListViewModel, FragmentRestaurantListBinding>() {

    override fun getViewBinding(): FragmentRestaurantListBinding =
        FragmentRestaurantListBinding.inflate(layoutInflater)

    private val restaurantCategory: RestaurantCategory by lazy {
        arguments?.getSerializable(RESTAURANT_CATEGORY_KEY) as RestaurantCategory
    }

    private val resourcesProvider by inject<ResourcesProvider>()

    override val viewModel by viewModel<RestaurantListViewModel> {
        parametersOf(restaurantCategory)
    }

    private val adapter by lazy {
        ModelRecyclerAdapter<RestaurantModel, RestaurantListViewModel>(
            listOf(),
            viewModel,
            resourcesProvider,
            object: RestaurantListListener {
                override fun onClickItem(model: RestaurantModel) {
                    // TODO change to detail activity
                    showMessage(model.toString())
                }
            }
        )
    }

    override fun initViews() = with(binding) {
        restaurantRecyclerView.adapter = adapter
        restaurantRecyclerView.layoutManager = LinearLayoutManager(this@RestaurantListFragment.context)

        // TODO delete
        testView.text = context?.getText(restaurantCategory.categoryNameId)
    }

    private fun showMessage(message: String) =
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()



    override fun observeData() = with(viewModel) {
        restaurantData.observe(viewLifecycleOwner) {
            Log.d("TAG", "observeData: $it")
            adapter.submitList(it)
        }
    }

    companion object {
        const val RESTAURANT_CATEGORY_KEY = "RestaurantCategoryKey"

        fun newInstance(restaurantCategory: RestaurantCategory) : RestaurantListFragment {
            val bundle = Bundle().apply {
                putSerializable(RESTAURANT_CATEGORY_KEY, restaurantCategory)
            }

            return RestaurantListFragment().apply {
                arguments = bundle
            }
        }
    }
}