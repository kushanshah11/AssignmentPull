package com.kushan.gitpullrequest.di.module

import android.app.Application
import android.content.Context
import com.kushan.gitpullrequest.application.GitPullApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BaseModule (private val application: GitPullApp){

    @Singleton
    @Provides
    fun provideApp() : Application = application

    @Singleton
    @Provides
    fun provideAppContext() : Context = application

}