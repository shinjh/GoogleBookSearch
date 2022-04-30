package com.shinjh1253.googlebooksearch.ui.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.shinjh1253.googlebooksearch.R
import com.shinjh1253.googlebooksearch.core.extensions.viewBinding
import com.shinjh1253.googlebooksearch.databinding.ViewholderBooklistLoadingBinding
import com.shinjh1253.googlebooksearch.ui.viewholder.SearchBookLoadStateViewHolder

class SearchBookLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<SearchBookLoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): SearchBookLoadStateViewHolder {
        val binding = parent.viewBinding<ViewholderBooklistLoadingBinding>(R.layout.viewholder_booklist_loading)
        return SearchBookLoadStateViewHolder(binding, retry)
    }

    override fun onBindViewHolder(holder: SearchBookLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
}