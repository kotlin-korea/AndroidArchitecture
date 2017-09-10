package iammert.com.androidarchitecture.data

import iammert.com.androidarchitecture.data.Status.ERROR
import iammert.com.androidarchitecture.data.Status.LOADING
import iammert.com.androidarchitecture.data.Status.SUCCESS

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
class Resource<out T> private constructor(val status: Status, val data: T?, val message: String?) {
    companion object {

        fun <T> success(data: T): Resource<T> {
            return Resource(SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(LOADING, data, null)
        }
    }
}

