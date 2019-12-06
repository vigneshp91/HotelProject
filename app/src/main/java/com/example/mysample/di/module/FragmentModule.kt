package com.example.mysample.di.module


import com.example.mysample.ui.homeactivity.view.HomeActivityFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeHomeActivityFragment(): HomeActivityFragment

}