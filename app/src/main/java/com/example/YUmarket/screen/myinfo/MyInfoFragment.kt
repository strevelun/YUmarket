package com.example.YUmarket.screen.myinfo

import com.example.YUmarket.databinding.FragmentMyInfoBinding
import com.example.YUmarket.screen.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class MyInfoFragment : BaseFragment<MyInfoViewModel, FragmentMyInfoBinding>() {
    override val viewModel by viewModel<MyInfoViewModel>()

    override fun getViewBinding() =
        FragmentMyInfoBinding.inflate(layoutInflater)

    override fun observeData() {
    }

    companion object {
        const val TAG = "MyInfoFragment"

        fun newInstance() = MyInfoFragment()
    }
}