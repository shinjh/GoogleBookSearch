package com.shinjh1253.googlebooksearch.data.remote.service

import com.shinjh1253.googlebooksearch.data.remote.model.BooksResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {

    /**
     * 특정 키워드에 대한 서적 검색 정보를 가져온다
     *
     * @param query 특정 키워드
     *
     */
    @GET("/books/v1/volumes")
    fun searchBooks(
        @Query("q") q: String,
        @Query("startIndex") startIndex: Int?,
        @Query("maxResults") maxResults: Int?
    ): Single<BooksResponse>
}