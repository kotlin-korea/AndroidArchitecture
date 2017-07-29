package iammert.com.androidarchitecture.databinding

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import iammert.com.androidarchitecture.data.Resource
import iammert.com.androidarchitecture.ui.BaseAdapter

object ListBindingAdapter {

    @JvmStatic
    @Suppress("UNCHECKED_CAST")
    @BindingAdapter(value = "resource")
    fun setResource(recyclerView: RecyclerView, resource: Resource<*>?) {
        val adapter = recyclerView.adapter ?: return
        resource?.data ?: return

        (adapter as? BaseAdapter<*, Any>)?.setData((resource.data as List<Any>?)!!)
    }

}