package com.android.library.di

import com.android.library.network.di.NetworkModel
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModel::class])
@InstallIn(SingletonComponent::class)
class LibraryModel {

}