package iammert.com.androidarchitecture.ui

import android.support.v7.widget.RecyclerView

abstract class BaseAdapter<Type : RecyclerView.ViewHolder, in Data> : RecyclerView.Adapter<Type>() {

    abstract fun setData(data: List<@JvmSuppressWildcards Data>)

}