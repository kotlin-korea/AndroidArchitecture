package iammert.com.androidarchitecture.data

class Resource<out T> private constructor(val status: Status,
                                          val data: T? = null,
                                          val message: String? = null) {
    companion object {
        fun <T> success(data: T): Resource<T> = Resource(Status.SUCCESS, data)
        fun <T> error(message: String, data: T?): Resource<T> = Resource(Status.ERROR, data, message)
        fun <T> loading(data: T?): Resource<T> = Resource(Status.LOADING, data)
    }
}