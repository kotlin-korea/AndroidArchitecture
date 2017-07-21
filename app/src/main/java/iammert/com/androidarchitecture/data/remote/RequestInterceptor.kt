package iammert.com.androidarchitecture.data.remote

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by mertsimsek on 19/05/2017.
 */

class RequestInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url()

        val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", ApiConstants.API_KEY)
                .build()

        val request = originalRequest.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}
