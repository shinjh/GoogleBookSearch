package com.shinjh1253.googlebooksearch.ui.viewholder

import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.shinjh1253.googlebooksearch.databinding.ViewholderBooklistLoadingBinding

class SearchBookLoadStateViewHolder(
    private val binding: ViewholderBooklistLoadingBinding,
    private val retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.btnRetry.setOnClickListener { retry() }
    }

    fun bind(loadState: LoadState) {
        with(binding) {
            pbLoading.isVisible = loadState is LoadState.Loading
            btnRetry.isVisible = loadState !is LoadState.Loading
            tvErrorMessage.isVisible = loadState is LoadState.Error

            if (loadState is LoadState.Error) {
                tvErrorMessage.text = loadState.error.localizedMessage
            }
        }
    }
}