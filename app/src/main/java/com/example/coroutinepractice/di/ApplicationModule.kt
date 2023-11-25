package com.example.coroutinepractice.di

import com.example.coroutinepractice.api.MyApi
import com.example.coroutinepractice.dataBuilder.RetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
class ApplicationModule {

    @Singleton
    @Provides
    fun provideRetrofitInstance(): MyApi {
        return RetrofitInstance.retrofit
    }

}