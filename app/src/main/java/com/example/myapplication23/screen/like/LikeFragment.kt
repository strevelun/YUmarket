package com.example.myapplication23.screen.like

import com.example.myapplication23.databinding.FragmentLikeBinding
import com.example.myapplication23.screen.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class LikeFragment
    : BaseFragment<LikeViewModel, FragmentLikeBinding>() {

    override val viewModel by viewModel<LikeViewModel>()

    override fun getViewBinding(): FragmentLikeBinding =
        FragmentLikeBinding.inflate(layoutInflater)

    override fun observeData() {
    }

    companion object {
        const val TAG = "LikeFragment"

        fun newInstance() = LikeFragment()
    }
}