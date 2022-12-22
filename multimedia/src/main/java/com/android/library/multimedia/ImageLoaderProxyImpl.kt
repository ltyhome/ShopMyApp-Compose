package com.android.library.multimedia

import android.app.Application
import coil.ImageLoader

class ImageLoaderProxyImpl : ImageLoaderProxy {
    override fun newImageLoader(app: Application) =
        ImageLoader.Builder(app).respectCacheHeaders(false).build()
}