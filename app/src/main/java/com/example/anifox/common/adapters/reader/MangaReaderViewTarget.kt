package com.example.anifox.common.adapters.reader

import android.graphics.BitmapFactory
import android.graphics.PointF
import android.graphics.drawable.Drawable
import android.net.Uri
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.transition.Transition
import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView
import com.example.anifox.databinding.FragmentReaderBinding
import java.io.File

class MangaReaderViewTarget(binding: FragmentReaderBinding)
    : CustomViewTarget<SubsamplingScaleImageView, File>(binding.imageView) {
    override fun onResourceReady(resource: File, transition: Transition<in File>?) {
        val uri = ImageSource.uri(Uri.fromFile(resource))
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(resource.absolutePath, options)
        val imageWidth = options.outWidth
        val imageHeight = options.outHeight
        val scaling = (imageHeight.toFloat() / view.height.toFloat()) * (imageWidth.toFloat() / view.width.toFloat())
        view.setImage(uri)
        view.setScaleAndCenter((imageHeight.toFloat() / view.height.toFloat()) / scaling, PointF(0F, 0F))
        view.isZoomEnabled = false
    }

    override fun onLoadFailed(errorDrawable: Drawable?) {

    }

    override fun onResourceCleared(placeholder: Drawable?) {}
}