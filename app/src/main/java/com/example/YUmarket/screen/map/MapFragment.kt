package com.example.YUmarket.screen.map

import com.example.YUmarket.databinding.FragmentMapBinding
import com.example.YUmarket.screen.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class MapFragment : BaseFragment<MapViewModel, FragmentMapBinding>() {
    override val viewModel by viewModel<MapViewModel>()

    override fun getViewBinding(): FragmentMapBinding =
        FragmentMapBinding.inflate(layoutInflater)

    override fun observeData() {
    }

    companion object {
        const val TAG = "MapFragment"

        fun newInstance() = MapFragment()
    }
}