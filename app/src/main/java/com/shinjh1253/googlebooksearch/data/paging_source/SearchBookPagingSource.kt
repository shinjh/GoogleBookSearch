package com.shinjh1253.googlebooksearch.data.paging_source

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.shinjh1253.googlebooksearch.core.util.SchedulersFacade
import com.shinjh1253.googlebooksearch.data.remote.service.BookService
import com.shinjh1253.googlebooksearch.model.Book
import com.shinjh1253.googlebooksearch.model.toBook
import io.reactivex.Single

class SearchBookPagingSource(
    private val bookService: BookService,
    private val query: String
) : RxPagingSource<Int, Book>() {

    companion object {
        private const val SEARCH_BOOK_STARTING_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, Book>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Book>> {
        val pageNumber = params.key ?: SEARCH_BOOK_STARTING_PAGE_INDEX
        val pageSize = params.loadSize

        return bookService.searchBooks(q = query, startIndex = pageNumber * pageSize, maxResults = pageSize)
            .subscribeOn(SchedulersFacade.IO)
            .map { bookResponse -> bookResponse.items.map { it.toBook() } }
            .map { toLoadResult(it, pageNumber, pageSize) }
            .onErrorReturn { toLoadError(it) }
    }

    private fun toLoadResult(data: List<Book>, position: Int, pageSize: Int): LoadResult<Int, Book> {
        return LoadResult.Page(
            data = data,
            prevKey = if (position == SEARCH_BOOK_STARTING_PAGE_INDEX) null else position - 1,
            nextKey = if (data.size < pageSize) null else position + 1
        )
    }

    private fun toLoadError(throwable: Throwable): LoadResult.Error<Int, Book> {
        return LoadResult.Error(throwable)
    }
}