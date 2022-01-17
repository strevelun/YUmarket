package com.example.YUmarket.screen.orderlist

import com.example.YUmarket.databinding.FragmentOrderListBinding
import com.example.YUmarket.screen.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class OrderListFragment : BaseFragment<OrderListViewModel, FragmentOrderListBinding>() {
    override val viewModel by viewModel<OrderListViewModel>()

    override fun getViewBinding() =
        FragmentOrderListBinding.inflate(layoutInflater)

    override fun observeData() {
    }

    companion object {
        const val TAG = "OrderListFragment"

        fun newInstance(): OrderListFragment {
            return OrderListFragment()
        }
    }
}