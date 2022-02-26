package com.example.YUmarket.util.provider

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * Resource를 제공하는 provider interface
 * @author Namjin Jeong (main),
 * Geonwoo Kim, Heetae Heo, Doyeop Kim, Eunho Bae (sub)
 * @since 2022/01/18
 */
interface ResourcesProvider {

    fun getString(@StringRes resId: Int): String

    fun getString(@StringRes resId: Int, vararg formArgs: Any): String

    fun getColor(@ColorRes resId: Int): Int

    fun getColorStateList(@ColorRes resId: Int): ColorStateList

    fun getDrawable(@DrawableRes resId: Int): Drawable?

}