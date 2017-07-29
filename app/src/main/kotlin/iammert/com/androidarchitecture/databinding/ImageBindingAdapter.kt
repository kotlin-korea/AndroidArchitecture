package iammert.com.androidarchitecture.databinding

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso
import iammert.com.androidarchitecture.data.remote.ApiConstants
import iammert.com.androidarchitecture.R

object ImageBindingAdapter {

    @JvmStatic
    @BindingAdapter(value = "url")
    fun loadImageUrl(view: ImageView, url: String?) {
        if (!(url.isNullOrBlank())) {
            Picasso.with(view.context)
                    .load(ApiConstants.IMAGE_ENDPOINT_PREFIX + url)
                    .placeholder(R.drawable.placeholder)
                    .into(view)
        }
    }

}