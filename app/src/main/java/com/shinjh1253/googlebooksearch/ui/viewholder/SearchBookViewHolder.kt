package com.shinjh1253.googlebooksearch.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.shinjh1253.googlebooksearch.databinding.ViewholderSearchBookBinding
import com.shinjh1253.googlebooksearch.model.Book

class SearchBookViewHolder(
    private val binding: ViewholderSearchBookBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(book: Book) {
        binding.apply {
            data = book
        }
    }
}
