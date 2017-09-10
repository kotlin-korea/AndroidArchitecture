package iammert.com.androidarchitecture.ui

import android.support.v7.widget.RecyclerView

/**
 * Created by mertsimsek on 21/05/2017.
 */

abstract class BaseAdapter<Type : RecyclerView.ViewHolder, in Data> : RecyclerView.Adapter<Type>() {

    abstract fun setData(data: List<Data>)
}