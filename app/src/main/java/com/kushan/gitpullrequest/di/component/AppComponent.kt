package com.kushan.gitpullrequest.di.component

import com.kushan.gitpullrequest.application.GitPullApp
import com.kushan.gitpullrequest.di.module.*
import com.kushan.gitpullrequest.di.module.viewmodel.ViewModelModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
    BaseModule::class,
    NetworkModule::class,
    BuilderModule::class,
    RepositoryModule::class,
    SchedulerModule::class,
    ViewModelModule::class])
interface AppComponent {
    fun inject(app: GitPullApp)
}