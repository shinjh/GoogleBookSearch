package com.shinjh1253.googlebooksearch.model

import com.shinjh1253.googlebooksearch.data.remote.model.BookModel

data class Book(
    val id: String,
    val title: String,
    val description: String,
    val authors: List<String>,
    val publishedDate: String,
    val smallThumbnail: String,
    val thumbnail: String,
    val saleability: String,
    val listPriceAmount: Int,
    val listPricecurrencyCode: String
)

/**
 * Mapping extension function [BookModel] -> [Book]
 **/
fun BookModel.toBook(): Book = Book(
    id = id,
    title = volumeInfo.title,
    description = volumeInfo.description ?: "",
    authors = volumeInfo.authors ?: listOf(),
    publishedDate = volumeInfo.publishedDate ?: "",
    smallThumbnail = volumeInfo.imageLinks?.smallThumbnail ?: "",
    thumbnail = volumeInfo.imageLinks?.thumbnail ?: "",
    saleability = saleInfo.saleability,
    listPriceAmount = saleInfo.retailPrice?.amount ?: 0,
    listPricecurrencyCode = saleInfo.retailPrice?.currencyCode ?: ""
)