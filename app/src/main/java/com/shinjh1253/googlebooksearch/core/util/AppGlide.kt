package com.shinjh1253.googlebooksearch.core.util

import android.content.Context
import android.util.Log
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Priority
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions

/**
 *  Glide 기본 설정에 관한 모듈 클래스
 */
@GlideModule
class AppGlide : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        val calculator = MemorySizeCalculator.Builder(context).build()

        val bitmapPoolSize = calculator.bitmapPoolSize
        val memoryCacheSize = calculator.memoryCacheSize
        val diskCacheSize = 1024 * 1024 * 50
        val scaleValue = 0.25f
        val timeout = 1000 * 10

        val requestOptions = RequestOptions()
            .priority(Priority.NORMAL)
            .format(DecodeFormat.PREFER_RGB_565)
            .sizeMultiplier(scaleValue)
            .timeout(timeout)


        builder.setBitmapPool(LruBitmapPool(bitmapPoolSize.toLong()))
            .setMemoryCache(LruResourceCache(memoryCacheSize.toLong()))
            .setDiskCache(InternalCacheDiskCacheFactory(context, "image_disk_cache", diskCacheSize.toLong()))
            .setDefaultRequestOptions(requestOptions)
            .setLogLevel(Log.ERROR)
    }

    override fun isManifestParsingEnabled(): Boolean {
        return false
    }
}
