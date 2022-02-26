package com.example.myapplication23.screen.map.MapProductInfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.YUmarket.extensions.load
import com.example.myapplication23.R
import com.example.myapplication23.databinding.ActivityMainBinding
import com.example.myapplication23.databinding.ActivityMapProductInfoBinding
import com.example.myapplication23.model.map.MapItemModel

class MapProductInfoActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMapProductInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapProductInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    fun initViews(){
        val item = intent.extras?.getParcelable<MapItemModel>("model")

        with(binding){
            if (item != null) {
                ivProductInfoProfile.load(item.itemImageUrl)
            }
            tvProductName.text = item?.itemName
            tvDiscountedPrice.text = Math.round((item!!.originalPrice * (100.0f - item!!.discountRate) * 0.01f))
                .toString() + "원"
        }
    }
}