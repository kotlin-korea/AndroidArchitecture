package iammert.com.androidarchitecture.databinding

import android.databinding.BindingAdapter
import android.widget.ImageView

import com.squareup.picasso.Picasso

import iammert.com.androidarchitecture.R
import iammert.com.androidarchitecture.data.remote.ApiConstants

/**
 * Created by mertsimsek on 20/05/2017.
 */
@BindingAdapter(value = "url")
fun loadImageUrl(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Picasso.with(view.context)
                .load(ApiConstants.IMAGE_ENDPOINT_PREFIX + url)
                .placeholder(R.drawable.placeholder)
                .into(view)
    }
}
