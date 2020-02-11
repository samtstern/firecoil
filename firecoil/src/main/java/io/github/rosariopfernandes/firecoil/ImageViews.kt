package io.github.rosariopfernandes.firecoil

import android.widget.ImageView
import coil.ComponentRegistry
import coil.ImageLoaderBuilder
import coil.api.loadAny
import coil.request.LoadRequestBuilder
import coil.request.RequestDisposable
import com.google.firebase.storage.StorageReference

inline fun ImageView.load(
    data: StorageReference,
    builder: LoadRequestBuilder.() -> Unit = {}
): RequestDisposable {
    val imageLoader = ImageLoaderBuilder(context)
        .componentRegistry(
            ComponentRegistry.Builder()
                .add(StorageReferenceFetcher())
                .build()
        )
        .build()
    return imageLoader.loadAny(context, data) {
        target(this@load)
        builder()
    }
}
