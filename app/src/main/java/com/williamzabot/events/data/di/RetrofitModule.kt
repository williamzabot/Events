package com.williamzabot.events.data.di

import com.williamzabot.events.data.api.EventApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideEventApi() = providerWebService<EventApi>()

    private inline fun <reified T> providerWebService(): T {
        return Retrofit.Builder()
            .baseUrl("http://5f5a8f24d44d640016169133.mockapi.io/api/")
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(T::class.java)
    }

    private fun provideOkHttpClient() = OkHttpClient.Builder().apply {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        addInterceptor(logging)
    }.build()
}