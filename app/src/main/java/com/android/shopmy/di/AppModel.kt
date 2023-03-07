package com.android.shopmy.di

import com.android.library.di.LibraryModel
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [LibraryModel::class])
@InstallIn(SingletonComponent::class)
class AppModel
