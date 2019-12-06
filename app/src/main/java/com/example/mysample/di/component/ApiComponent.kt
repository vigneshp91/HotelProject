package com.example.mysample.di.component


import android.app.Application
import com.example.mysample.di.module.ViewModelModule

import com.example.mysample.MyApplication
import com.example.mysample.di.module.ActivityModule
import com.example.mysample.di.module.ApiModule
import com.example.mysample.di.module.DbModule
import com.example.mysample.di.module.FragmentModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

import javax.inject.Singleton

@Singleton
@Component(
    modules = [ApiModule::class, DbModule::class,
        ViewModelModule::class, AndroidSupportInjectionModule::class,
        ActivityModule::class, FragmentModule::class]
)
interface ApiComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun apiModule(apiModule: ApiModule): Builder

        @BindsInstance
        fun dbModule(dbModule: DbModule): Builder

        fun build(): ApiComponent
    }

    fun inject(appController: MyApplication)
}
