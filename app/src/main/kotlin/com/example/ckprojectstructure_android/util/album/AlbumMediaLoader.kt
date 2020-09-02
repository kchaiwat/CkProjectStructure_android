package com.example.ckprojectstructure_android.util.album

import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import com.yanzhenjie.album.AlbumFile
import com.yanzhenjie.album.AlbumLoader

class AlbumMediaLoader : AlbumLoader {

    override fun load(imageView: ImageView, albumFile: AlbumFile) {
        load(imageView, albumFile.path)
    }

    override fun load(imageView: ImageView, url: String) {
        val requestOptions = RequestOptions()
//            .error(R.drawable.vector_error_100dp)
//            .placeholder(R.drawable.vector_image_100dp)

//        GlideApp.with(imageView.context)
//            .load(url)
//            .thumbnail(0.1f)
//            .apply(requestOptions)
//            .into(imageView)
    }
}