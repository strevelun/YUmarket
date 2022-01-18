package com.example.YUmarket.extensions

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory

/**
 * ImageView의 기능 확장을 모아둔 kotlin file
 * @author Doyeop Kim (main)
 * Geonwoo Kim, Heetae Heo, Namjin Jeong, Eunho Bae (sub)
 * @since 2022/01/18
 */

// fade-in, fade-out의 애니메이션을 처리하기 위한 factory
private val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()

fun ImageView.clear() = Glide.with(context).clear(this)

fun ImageView.load(url: String, corner: Float = 0f, scaleType: Transformation<Bitmap> = CenterInside()) {
    Glide.with(this)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade(factory)) // fade-in 애니메이션
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .apply {
            if (corner > 0) transforms(scaleType, RoundedCorners(corner.fromDpToPx())) // 이미지의 라운딩 처리
        } // 라운딩 처리
        .into(this)
}