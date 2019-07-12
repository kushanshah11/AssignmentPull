package com.kushan.gitpullrequest.di.module


import com.kushan.gitpullrequest.di.module.builder.PullRequestBuilderModule
import com.kushan.gitpullrequest.feature.pullRequest.PullRequestActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class BuilderModule {


    @ContributesAndroidInjector(modules = [PullRequestBuilderModule::class])
    abstract fun contributeOpenPullRequestActivity(): PullRequestActivity

}