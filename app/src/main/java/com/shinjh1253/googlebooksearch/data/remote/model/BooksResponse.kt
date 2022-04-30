package com.shinjh1253.googlebooksearch.data.remote.model

data class BooksResponse(
    val kind: String,
    val totalItems: Int,
    val items: List<BookModel>
)

data class BookModel(
    val kind: String,
    val id: String,
    val selfLink: String,
    val volumeInfo: VolumeInfoModel,
    val saleInfo: SaleInfoModel
)

data class VolumeInfoModel(
    val title: String,
    val authors: List<String>?,
    val description: String?,
    val publishedDate: String?,
    val imageLinks: ImageLinksModel?,
    val language: String?
)

data class SaleInfoModel(
    val country: String,
    val saleability: String,
    val isEbook: Boolean,
    val retailPrice: PriceModel?
)

data class ImageLinksModel(
    val smallThumbnail: String,
    val thumbnail: String
)

data class PriceModel(
    val amount: Int,
    val currencyCode: String
)