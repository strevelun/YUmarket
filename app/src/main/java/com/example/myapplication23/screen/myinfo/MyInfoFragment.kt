package com.example.myapplication23.screen.myinfo

import com.example.myapplication23.databinding.FragmentMyInfoBinding
import com.example.myapplication23.screen.base.BaseFragment
import com.example.myapplication23.screen.like.LikeFragment
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