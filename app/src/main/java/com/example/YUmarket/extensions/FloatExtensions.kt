package com.example.YUmarket.extensions

import android.content.res.Resources

/**
 * Float의 기능 확장을 모아둔 kotlin file
 * @author Doyeop Kim (main),
 * Geonwoo Kim, Heetae Heo, Namjin Jeong, Eunho Bae (sub)
 * @since 2022/01/18
 */
fun Float.fromDpToPx(): Int {
    return (this * Resources.getSystem().displayMetrics.density).toInt()
}