package com.shinjh1253.googlebooksearch.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.shinjh1253.googlebooksearch.data.paging_source.SearchBookPagingSource
import com.shinjh1253.googlebooksearch.data.remote.service.BookService
import com.shinjh1253.googlebooksearch.model.Book
import io.reactivex.Flowable
import javax.inject.Inject

class SearchBookRepositoryImpl @Inject constructor(
    private val bookService: BookService
) : SearchBookRepository {

    override fun searchBookPagingData(query: String, initialPageSize: Int, pageSize: Int): Flowable<PagingData<Book>> = Pager(
        config = PagingConfig(pageSize = pageSize, prefetchDistance = pageSize * 3, initialLoadSize = pageSize),
        initialKey = initialPageSize,
        pagingSourceFactory = { SearchBookPagingSource(bookService, query) }
    ).flowable
}