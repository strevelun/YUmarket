package com.example.myapplication23.util

import androidx.lifecycle.MutableLiveData

object LocationData {
    val locationStateLiveData = MutableLiveData<LocationState>(LocationState.Uninitialized)
}