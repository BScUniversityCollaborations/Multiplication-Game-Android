package com.unipi.p17172p17168p17164.multiplicationgame.utils

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey

/**
 * A custom object to create a common functions for Glide which can be used in whole application.
 */
@GlideModule
class GlideLoader(val context: Context) : AppGlideModule() {
    
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)
        builder.apply{ RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
            .signature(
                ObjectKey(System.currentTimeMillis().toShort())
            )
        }
    }
}