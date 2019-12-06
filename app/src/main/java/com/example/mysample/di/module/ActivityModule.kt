package com.example.mysample.di.module


import com.example.mysample.ui.homeactivity.view.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeHomeActivity(): HomeActivity
}
