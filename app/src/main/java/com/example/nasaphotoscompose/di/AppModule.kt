package com.example.nasaphotoscompose.di

import android.app.Application
import android.content.Context
import com.example.nasaphotoscompose.service.NasaApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@ExperimentalSerializationApi
@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun context(application: Application): Context = application.applicationContext

    private val BASE_URL = "https://api.nasa.gov"
    private val API_KEY = "ItXjX7uV4euA35LozgIfhSIK1st5pYPfl1qAsFl0"
    private val connectionTimeout: Int = 10000

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(connectionTimeout.toLong(), TimeUnit.MILLISECONDS)
            .addInterceptor { chain ->
                val original = chain.request()
                val originalHttpUrl = original.url
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", API_KEY)
                    .build()
                val requestBuilder = original.newBuilder()
                    .url(url)
                chain.proceed(requestBuilder.build())
            }
            .build()
    }


    @Provides
    @Singleton
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): NasaApi {
        return retrofit.create(NasaApi::class.java)
    }

}