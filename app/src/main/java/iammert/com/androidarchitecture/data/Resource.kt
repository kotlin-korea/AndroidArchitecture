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

import iammert.com.androidarchitecture.data.Status.ERROR
import iammert.com.androidarchitecture.data.Status.LOADING
import iammert.com.androidarchitecture.data.Status.SUCCESS


/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
class Resource<out T> private constructor(val status: Status, val data: T?, val message: String?) {
    companion object {

        @JvmStatic
        fun <T> success(data: T): Resource<T> =
            Resource(SUCCESS, data, null)

        @JvmStatic
        fun <T> error(msg: String, data: T?): Resource<T> =
            Resource(ERROR, data, msg)

        @JvmStatic
        fun <T> loading(data: T?): Resource<T> =
            Resource(LOADING, data, null)
    }
}
