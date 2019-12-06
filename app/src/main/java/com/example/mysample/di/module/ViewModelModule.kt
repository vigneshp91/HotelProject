package com.example.mysample.di.module


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mysample.di.factory.ViewModelFactory
import com.example.mysample.ui.homeactivity.viewmodel.HomeActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeActivityViewModel::class)
    protected abstract fun homeActivityViewModel(moviesListViewModel: HomeActivityViewModel): ViewModel



}