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

/**
 * Created by mertsimsek on 20/05/2017.
 */
@Module(includes = arrayOf(ViewModelModule::class))
class AppModule {

    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(ApiConstants.TIMEOUT_IN_SEC.toLong(), TimeUnit.SECONDS)
            .readTimeout(ApiConstants.TIMEOUT_IN_SEC.toLong(), TimeUnit.SECONDS)
            .addInterceptor(RequestInterceptor())
            .build()

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .baseUrl(ApiConstants.ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    internal fun provideService(retrofit: Retrofit): MovieDBService =
            retrofit.create(MovieDBService::class.java)

    @Provides
    @Singleton
    internal fun provideMovieDatabase(application: Application): MovieDatabase =
            Room.databaseBuilder(application, MovieDatabase::class.java, "aa.db")
                    .build()

    @Provides
    @Singleton
    internal fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao =
            movieDatabase.movieDao()

}
