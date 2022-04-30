package com.shinjh1253.googlebooksearch.core.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.shinjh1253.googlebooksearch.core.extensions.viewBinding

/**
 * DataBinding 을 지원하기 위한 abstract class
 */
abstract class BindingActivity<T : ViewDataBinding> constructor(
    @LayoutRes private val layoutResId: Int
) : AppCompatActivity() {

    private var _binding: T? = null
    protected val binding: T
        get() = checkNotNull(_binding) {
            "AppCompatActivity $this binding cannot be accessed before onCreate() or after onDestroy()"
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = viewBinding(layoutResId)
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding?.unbind()
        _binding = null
    }
}