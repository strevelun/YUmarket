package com.example.YUmarket.screen.home

import androidx.lifecycle.viewModelScope
import com.example.YUmarket.screen.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {

    override fun fetchData(): Job = viewModelScope.launch {
        // TODO get from repository
    }

}