package com.example.YUmarket.util

import androidx.lifecycle.MutableLiveData

object LocationData {
    val locationStateLiveData = MutableLiveData<LocationState>(LocationState.Uninitialized)
}