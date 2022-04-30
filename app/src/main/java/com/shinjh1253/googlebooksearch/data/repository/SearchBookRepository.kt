package com.shinjh1253.googlebooksearch.data.repository

import androidx.paging.PagingData
import com.shinjh1253.googlebooksearch.model.Book
import io.reactivex.Flowable

interface SearchBookRepository {

    /**
     * query에 해당하는 서적을 검색한다.
     *
     * @return query에 해당하는 서적 리스트 [Book]
     */
    fun searchBookPagingData(
        query: String,
        initialPageSize: Int,
        pageSize: Int
    ): Flowable<PagingData<Book>>
}