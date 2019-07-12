package com.kushan.gitpullrequest.feature.repository

import com.kushan.gitpullrequest.feature.PullRequestDisplayData
import io.reactivex.Observable


interface PullRequestRepo{
    fun fetchOpenPullRequest(ownerName : String, repoName : String) : Observable<List<PullRequestDisplayData>>
}