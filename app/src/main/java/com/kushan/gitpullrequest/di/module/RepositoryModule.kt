package com.kushan.gitpullrequest.di.module

import com.kushan.gitpullrequest.feature.repository.PullRequestRepo
import com.kushan.gitpullrequest.feature.repository.impl.PullRequestImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun providePullRequestRepositoryRepository(pullRequestRepositoryImpl: PullRequestImpl): PullRequestRepo
}