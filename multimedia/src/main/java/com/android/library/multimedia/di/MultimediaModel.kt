package com.android.library.multimedia.di

import com.android.library.multimedia.ImageLoaderProxyImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MultimediaModel {

    @Provides
    fun provideImageLoader() = ImageLoaderProxyImpl()
}