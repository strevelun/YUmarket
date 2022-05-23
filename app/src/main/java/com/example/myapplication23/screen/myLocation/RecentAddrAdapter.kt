package com.example.myapplication23.screen.myLocation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication23.R
import com.example.myapplication23.data.entity.database.AddressHistoryEntity
import com.example.myapplication23.data.entity.location.MapSearchInfoEntity
import com.example.myapplication23.databinding.ActivityMyLocationBinding
import com.example.myapplication23.databinding.RecyclerRecentAddrItemBinding

class RecentAddrAdapter(val itemClick:(AddressHistoryEntity) -> Unit) : RecyclerView.Adapter<RecentAddrAdapter.ViewHolder>() {

    var datas = mutableListOf<AddressHistoryEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val b = RecyclerRecentAddrItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(b, itemClick)
    }

    override fun getItemCount(): Int = datas.size

    fun clear() { datas.clear() }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(private val binding: RecyclerRecentAddrItemBinding, itemClick : (AddressHistoryEntity) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AddressHistoryEntity) {
            binding.tvAddr.text = item.name
            binding.root.setOnClickListener { itemClick(item) }
        }
    }


}