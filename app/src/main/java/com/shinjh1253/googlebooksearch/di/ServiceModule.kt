package com.shinjh1253.googlebooksearch.di

import com.shinjh1253.googlebooksearch.data.remote.service.BookService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    fun provideBookService(retrofit: Retrofit): BookService = retrofit.create(BookService::class.java)
}