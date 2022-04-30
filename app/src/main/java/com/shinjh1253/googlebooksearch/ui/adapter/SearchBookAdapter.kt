package com.shinjh1253.googlebooksearch.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.shinjh1253.googlebooksearch.R
import com.shinjh1253.googlebooksearch.core.extensions.viewBinding
import com.shinjh1253.googlebooksearch.databinding.ViewholderSearchBookBinding
import com.shinjh1253.googlebooksearch.model.Book
import com.shinjh1253.googlebooksearch.ui.viewholder.SearchBookViewHolder

class SearchBookAdapter() : PagingDataAdapter<Book, SearchBookViewHolder>(COMPARATOR) {

    override fun onBindViewHolder(holder: SearchBookViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchBookViewHolder {
        val binding = parent.viewBinding<ViewholderSearchBookBinding>(R.layout.viewholder_search_book)
        return SearchBookViewHolder(binding)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem == newItem
            }
        }
    }
}