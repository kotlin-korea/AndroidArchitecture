/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

abstract class NetworkBoundResource<ResultType, RequestType> @MainThread internal constructor() {
    private val result = MediatorLiveData<Resource<ResultType>>().apply {
        value = Resource.loading<ResultType>()
        val dbSource = loadFromDb()
        addSource(dbSource) { data ->
            removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                addSource(dbSource) { newData ->
                    newData?.let {
                        value = Resource.success(it)
                    }
                }
            }
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        result.addSource(dbSource) { newData -> result.value = Resource.loading(newData) }
        createCall().enqueue(object : Callback<RequestType> {
            override fun onResponse(call: Call<RequestType>, response: Response<RequestType>) {
                result.removeSource(dbSource)
                saveResultAndReInit(response.body())
            }

            override fun onFailure(call: Call<RequestType>, t: Throwable) {
                onFetchFailed()
                result.removeSource(dbSource)
                result.addSource(dbSource) { newData ->
                    result.value = Resource.error(t.message ?: "", newData)
                }
            }
        })
    }

    @MainThread
    @SuppressLint("StaticFieldLeak")
    private fun saveResultAndReInit(response: RequestType) {
        object : AsyncTask<Unit, Unit, Unit?>() {

            override fun doInBackground(vararg param: Unit?): Unit? {
                saveCallResult(response)
                return null
            }

            override fun onPostExecute(aVoid: Unit?) {
                result.addSource(loadFromDb()) { newData ->
                    newData?.let {
                        result.value = Resource.success(it)
                    }
                }
            }
        }.execute()
    }

    @WorkerThread
    protected abstract fun saveCallResult(item: RequestType?)

    @MainThread
    protected fun shouldFetch(data: ResultType?): Boolean {
        return true
    }

    @MainThread
    protected abstract fun loadFromDb(): LiveData<ResultType>

    @MainThread
    protected abstract fun createCall(): Call<RequestType>

    @MainThread
    protected fun onFetchFailed() {
    }

    val asLiveData: LiveData<Resource<ResultType>>
        get() = result
}
