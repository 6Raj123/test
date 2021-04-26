package com.raj.mymusicapp.di

import com.raj.mymusicapp.ui.HomeActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [RetrofitModule::class])
interface RetrofitComponent {
    fun inject(activity: HomeActivity?)
}