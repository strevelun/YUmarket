package com.example.YUmarket.model.homelist

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.YUmarket.model.CellType
import com.example.YUmarket.model.Model
import com.example.YUmarket.model.homelist.category.HomeListCategory

/**
 * HomeList에서만 사용이 되는 Model의 추상적 구조를 정의한 클래슴
 * @author Doyeop Kim (main),
 * Geonwoo Kim, Heetae Heo, Namjin Jeong, Eunho Bae (sub)
 * @since 2022/01/18
 */
abstract class HomeListModel(
    override val id: Long,
    override val type: CellType = CellType.HOME_CELL,
    open val homeListCategory: HomeListCategory
) : Model(id, type) {

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<HomeListModel> =
            object : DiffUtil.ItemCallback<HomeListModel>() {

                override fun areItemsTheSame(
                    oldItem: HomeListModel,
                    newItem: HomeListModel
                ): Boolean {
                    return oldItem.id == newItem.id && oldItem.type == newItem.type && oldItem.homeListCategory == newItem.homeListCategory
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldItem: HomeListModel,
                    newItem: HomeListModel
                ): Boolean {
                    return oldItem === newItem
                }
            }
    }

}
