package com.jaegersoft.weather.di

import com.jaegersoft.weather.model.api.ContentInterface
import com.jaegersoft.weather.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RepositoryModule {
    @Singleton
    @Provides
    fun provideWeatherRepository(
        contentRetrofit : ContentInterface
    ) : WeatherRepository {
        return WeatherRepository(contentRetrofit)
    }
}