package com.shinjh1253.googlebooksearch.ui

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.ConcatAdapter
import com.shinjh1253.googlebooksearch.R
import com.shinjh1253.googlebooksearch.core.base.BindingActivity
import com.shinjh1253.googlebooksearch.core.extensions.createAlertDialog
import com.shinjh1253.googlebooksearch.databinding.ActivityMainBinding
import com.shinjh1253.googlebooksearch.ui.adapter.SearchBookAdapter
import com.shinjh1253.googlebooksearch.ui.adapter.SearchBookLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel by viewModels<MainViewModel>()

    // 메시지를 보여주기 위한 다이어로그
    private var alertDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            lifecycleOwner = this@MainActivity
            viewModel = this@MainActivity.viewModel
        }

        setupRecyclerView()
        observeViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()

        alertDialog = null
    }

    private fun setupRecyclerView() {
        val searchBookAdapter = SearchBookAdapter()

        binding.rvSearchBook.apply {
            setHasFixedSize(true)
            adapter = searchBookAdapter.withLoadStateFooter(SearchBookLoadStateAdapter { searchBookAdapter.retry() })
        }
    }

    private fun observeViewModel() {
        viewModel.viewEvent.observe(this) {
            it.getContentIfNotHandled()?.let { event ->
                when (event) {
                    is MainViewEvent.HideKeyboard -> hideKeyboard()
                    else -> { /* Nothing to do.. */
                    }
                }
            }
        }

        viewModel.errorMessageEvent.observe(this) {
            it.getContentIfNotHandled()?.let { event ->
                when (event) {
                    is ErrorViewEvent.ShowErrorDialog -> showAlertDialog(getString(R.string.error), event.throwable.localizedMessage)
                    else -> { /* Nothing to do.. */
                    }
                }
            }
        }

        viewModel.bookListPagingData.observe(this) {
            binding.rvSearchBook.run {
                ((adapter as ConcatAdapter).adapters[0] as SearchBookAdapter).submitData(lifecycle, it)
            }
        }
    }

    private fun hideKeyboard() {
        binding.root.run {
            clearFocus()
            (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(windowToken, 0)
        }
    }

    private fun showAlertDialog(title: String, message: String?) {
        alertDialog ?: kotlin.run {
            alertDialog = createAlertDialog(title, message) {
                alertDialog = null
            }
        }

        alertDialog?.show()
    }

}