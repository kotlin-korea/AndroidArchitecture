package iammert.com.androidarchitecture.data.remote

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class RequestInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response =
            chain.proceed(request(chain.request()))

    private fun request(request: Request): Request =
            request.newBuilder()
                    .url(
                            request.url().newBuilder()
                                    .addQueryParameter("api_key", ApiConstants.API_KEY)
                                    .build())
                    .build()
}