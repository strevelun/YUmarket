package com.example.YUmarket.util.provider

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat

/**
 * ResourcesProvider의 구현체
 * @author Namjin Jeong (main),
 * Geonwoo Kim, Heetae Heo, Doyeop Kim, Eunho Bae (sub)
 * @since 2022/01/18
 */
class DefaultResourcesProvider(
    private val context: Context
) : ResourcesProvider {

    override fun getString(resId: Int): String = context.getString(resId)

    override fun getString(resId: Int, vararg formArgs: Any): String =
        context.getString(resId, *formArgs)

    override fun getColor(resId: Int): Int = ContextCompat.getColor(context, resId)

    override fun getColorStateList(resId: Int): ColorStateList = getColorStateList(resId)

    override fun getDrawable(resId: Int): Drawable? = context.getDrawable(resId)
}