package com.kushan.gitpullrequest.di.module.builder


import com.kushan.gitpullrequest.feature.pullRequest.PullRequestFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class PullRequestBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeOpenPullRequestFragment(): PullRequestFragment

}