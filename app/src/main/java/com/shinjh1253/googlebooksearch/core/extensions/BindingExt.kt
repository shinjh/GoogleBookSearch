package com.shinjh1253.googlebooksearch.core.extensions

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * activity binding
 */
fun <T : ViewDataBinding> Activity.viewBinding(
    @LayoutRes layoutResId: Int,
    bindingComponent: DataBindingComponent? = null
): T = DataBindingUtil.setContentView(this, layoutResId, bindingComponent)

/**
 * viewGroup binding (viewHolder)
 */
fun <T : ViewDataBinding> ViewGroup.viewBinding(
    @LayoutRes layoutResId: Int,
    bindingComponent: DataBindingComponent? = null
): T = DataBindingUtil.inflate(LayoutInflater.from(context), layoutResId, this, false, bindingComponent)
