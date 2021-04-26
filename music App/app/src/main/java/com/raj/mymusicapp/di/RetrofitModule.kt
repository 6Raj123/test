package com.raj.mymusicapp.di

import com.raj.mymusicapp.network.Url
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class RetrofitModule {

@Singleton
@Provides
fun provideRetrofit(): Retrofit{
    return Retrofit.Builder()
        .baseUrl(Url.url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
}