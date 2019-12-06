package com.example.mysample

import android.app.Activity
import android.app.Application
import com.example.mysample.di.component.DaggerApiComponent
import com.example.mysample.di.module.ApiModule
import com.example.mysample.di.module.DbModule
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MyApplication:Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        DaggerApiComponent.builder()
            .application(this)
            .apiModule(ApiModule())
            .dbModule(DbModule())
            .build()
            .inject(this)
    }
}