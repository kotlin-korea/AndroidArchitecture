package iammert.com.androidarchitecture.di

import android.app.Application
import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import iammert.com.androidarchitecture.data.local.MovieDatabase
import iammert.com.androidarchitecture.data.local.dao.MovieDao
import iammert.com.androidarchitecture.data.remote.ApiConstants
import iammert.com.androidarchitecture.data.remote.MovieDBService
import iammert.com.androidarchitecture.data.remote.RequestInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = arrayOf(ViewModelModule::class))
class AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
            OkHttpClient.Builder()
                    .apply {
                        connectTimeout(ApiConstants.TIMEOUT_IN_SEC.toLong(), TimeUnit.SECONDS)
                        readTimeout(ApiConstants.TIMEOUT_IN_SEC.toLong(), TimeUnit.SECONDS)
                        addInterceptor(RequestInterceptor())
                    }.build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): MovieDBService =
            Retrofit.Builder()
                    .baseUrl(ApiConstants.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
                    .create(MovieDBService::class.java)

    @Provides
    @Singleton
    fun provideMovieDatabase(application: Application): MovieDatabase =
            Room.databaseBuilder(application, MovieDatabase::class.java, "aa.db").build()

    @Provides
    @Singleton
    fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao = movieDatabase.movieDao()

}
