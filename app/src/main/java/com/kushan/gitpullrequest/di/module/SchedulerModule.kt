package com.kushan.gitpullrequest.di.module

import com.kushan.gitpullrequest.common.Scheduler
import com.kushan.gitpullrequest.common.SchedulerImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class SchedulerModule {

     @Singleton
     @Provides
     open fun provideAppScheduler(): Scheduler = SchedulerImpl()
}