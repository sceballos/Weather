package com.jaegersoft.weather.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jaegersoft.weather.model.api.ContentInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {
    const val acessKey = ""
    @Singleton
    @Provides
    fun provideGsonBuilder() : Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson : Gson) : Retrofit.Builder {
        return Retrofit.Builder().
        baseUrl("https://livestest.free.beeceptor.com/").
        addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideContentService(retrofit: Retrofit.Builder) : ContentInterface {
        return retrofit.build().create(ContentInterface::class.java)
    }
}