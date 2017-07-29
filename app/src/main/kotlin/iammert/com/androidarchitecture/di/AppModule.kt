package iammert.com.androidarchitecture.di

import android.app.Application
import android.arch.persistence.room.Room

import java.util.concurrent.TimeUnit

import javax.inject.Singleton

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

/**
 * Created by mertsimsek on 20/05/2017.
 */
@Module(includes = arrayOf(ViewModelModule::class))
class AppModule {

    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder().apply {
            connectTimeout(ApiConstants.TIMEOUT_IN_SEC.toLong(), TimeUnit.SECONDS)
            readTimeout(ApiConstants.TIMEOUT_IN_SEC.toLong(), TimeUnit.SECONDS)
            addInterceptor(RequestInterceptor())
        }
        return okHttpClient.build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient): MovieDBService {
        val retrofit = Retrofit.Builder()
                .baseUrl(ApiConstants.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

        return retrofit.create(MovieDBService::class.java)
    }

    @Provides
    @Singleton
    internal fun provideMovieDatabase(application: Application): MovieDatabase {
        return Room.databaseBuilder(application, MovieDatabase::class.java, "aa.db").build()
    }

    @Provides
    @Singleton
    internal fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao {
        return movieDatabase.movieDao()
    }

}
