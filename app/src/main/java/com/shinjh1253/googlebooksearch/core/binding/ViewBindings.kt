package com.shinjh1253.googlebooksearch.core.binding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.shinjh1253.googlebooksearch.R
import com.shinjh1253.googlebooksearch.core.util.GlideApp

object ViewBindings {

    @JvmStatic
    @BindingAdapter(value = ["imageUrl", "placeHolder"], requireAll = false)
    fun bindGlide(view: ImageView, url: String?, placeHolder: Drawable?) {
        val ph = placeHolder ?: ContextCompat.getDrawable(view.context, R.drawable.ic_launcher_foreground)

        GlideApp.with(view)
            .load(url)
            .placeholder(ph)
            .error(ph)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(view)
    }
}