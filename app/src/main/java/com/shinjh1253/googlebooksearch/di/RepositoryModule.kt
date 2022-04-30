package com.shinjh1253.googlebooksearch.di

import com.shinjh1253.googlebooksearch.data.repository.SearchBookRepository
import com.shinjh1253.googlebooksearch.data.repository.SearchBookRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindSearchRepository(searchRepositoryImpl: SearchBookRepositoryImpl): SearchBookRepository
}