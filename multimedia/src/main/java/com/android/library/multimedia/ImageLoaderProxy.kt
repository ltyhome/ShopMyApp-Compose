package com.android.library.multimedia

import android.app.Application
import coil.ImageLoader

interface ImageLoaderProxy {
    fun newImageLoader(app: Application): ImageLoader
}