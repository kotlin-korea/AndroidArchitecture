package iammert.com.androidarchitecture.data

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.os.AsyncTask
import android.support.annotation.MainThread
import android.support.annotation.WorkerThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class NetworkBoundResource<ResultType, RequestType> @MainThread constructor() {

    private val result = MediatorLiveData<Resource<ResultType>>().apply {
        value = Resource.loading(null)
    }

    @get:Synchronized
    val asLiveData: LiveData<Resource<ResultType>> by lazy {
        val dbSource = loadFromDb()

        result.addSource(dbSource, {
            result.removeSource(dbSource)

            if (shouldFetchData(it)) {
                fetchFromNetwork(dbSource)
            } else {
                fetchFromDb(dbSource)
            }
        })

        result
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        result.addSource(dbSource, { data -> result.value = Resource.loading(data) })
        createCall().enqueue(object : Callback<RequestType> {
            override fun onResponse(call: Call<RequestType>, response: Response<RequestType>) {
                result.removeSource(dbSource)
                saveResultAndReInit(response.body())
            }

            override fun onFailure(call: Call<RequestType>, t: Throwable) {
                onFetchFailed()
                result.removeSource(dbSource)
                result.addSource(dbSource, { data -> result.value = Resource.error(t.message ?: "", data) })
            }
        })
    }

    @MainThread
    @SuppressLint("StaticFieldLeak")
    private fun saveResultAndReInit(response: RequestType) {
        object : AsyncTask<RequestType, Void, Void>() {
            override fun doInBackground(vararg params: RequestType): Void? {
                saveCallResult(params[0])
                return null
            }

            override fun onPostExecute(result: Void?) {
                this@NetworkBoundResource.result
                        .addSource(loadFromDb(), { data -> data?.let { Resource.success(it) } })
            }
        }.execute(response)
    }

    private fun fetchFromDb(dbSource: LiveData<ResultType>) {
        result.addSource(dbSource, { data ->
            data?.let { result.value = Resource.success<ResultType>(it) }
        })
    }

    @MainThread
    abstract fun loadFromDb(): LiveData<ResultType>

    @MainThread
    abstract fun createCall(): Call<RequestType>

    @WorkerThread
    abstract fun saveCallResult(result: RequestType)

    @MainThread
    open fun onFetchFailed() {
    }

    @MainThread
    fun shouldFetchData(resultType: ResultType?): Boolean = true

}