package iammert.com.androidarchitecture.databinding

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso
import iammert.com.androidarchitecture.R
import iammert.com.androidarchitecture.data.remote.ApiConstants

@BindingAdapter(value = "url")
fun loadImageUrl(view: ImageView, url: String?) =
        url.takeIf { it.isNullOrEmpty().not() }?.let {
            Picasso.with(view.context)
                    .load(ApiConstants.IMAGE_ENDPOINT_PREFIX + it)
                    .placeholder(R.drawable.placeholder)
                    .into(view)
        }
