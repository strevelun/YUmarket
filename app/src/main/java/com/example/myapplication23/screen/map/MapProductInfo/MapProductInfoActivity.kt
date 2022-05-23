package com.example.myapplication23.screen.map.MapProductInfo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.YUmarket.extensions.load
import com.example.myapplication23.R
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
            // tvStoreName.text = item?.
            tvStockQuantity.text = item?.stockQuantity.toString() + "개 남음"
            tvOriginalPrice.text = item?.originalPrice.toString()
            tvDiscountedPrice.text = Math.round((item!!.originalPrice * (100.0f - item!!.discountRate) * 0.01f)).toString() + "원"
        }

    }

    // TODO : 삭제 예정
    inner class TestAdapter(private val context: Context) : RecyclerView.Adapter<TestAdapter.ViewHolder>() {

        private var items = mutableListOf<ReviewItemModel>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

            val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_review_item, parent, false)

            return ViewHolder(view)
        }
        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            holder.bind(items[position])
        }

         fun add() {
            items.add(ReviewItemModel())
        }

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            fun bind(item: ReviewItemModel) {
            }
        }
    }
}

