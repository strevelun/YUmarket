package com.example.YUmarket.model

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

abstract class Model(
    open val id : Long,
    open val type : CellType
) {

    /**
     * 두 Item이 같은지 판단하는 Method
     * @author Main 정남진, Sub 김건우, 김도엽, 배은호, 허희태
     * @since 2022.01.21
     * @param item 비교할 Item
     * @return 두 Item이 같으면 True, 다르면 False
     */
    open fun isTheSame(item: Model) =
        this.id == item.id && this.type == item.type

    companion object {
        val DIFF_CALLBACK = object: DiffUtil.ItemCallback<Model>() {
            override fun areItemsTheSame(oldItem: Model, newItem: Model): Boolean {
                return oldItem.isTheSame(newItem)
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Model, newItem: Model): Boolean {
                return oldItem === newItem
            }

        }
    }
}