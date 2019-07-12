package com.kushan.gitpullrequest.application

import android.app.Activity
import android.app.Application
import com.kushan.gitpullrequest.di.component.DaggerAppComponent
import com.kushan.gitpullrequest.di.module.BaseModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


class GitPullApp : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    private val appComponent by lazy {
        DaggerAppComponent.builder()
            .baseModule(BaseModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
}