package com.kushan.gitpullrequest.feature.repository.impl



import com.kushan.gitpullrequest.feature.PullRequestDisplayData
import com.kushan.gitpullrequest.feature.repository.PullRequestRepo
import com.kushan.gitpullrequest.network.exception.NoPullRequestFailure
import com.kushan.gitpullrequest.network.interfaces.PullRequestApi

import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class PullRequestImpl @Inject constructor(private val pullRequestApi: PullRequestApi,
                                          @Named("OAuthParams") private val OAuthParams : HashMap<String,String>)
                                :PullRequestRepo {

    override fun fetchOpenPullRequest(ownerName: String, repoName: String): Observable<List<PullRequestDisplayData>> {
        return pullRequestApi.getPullRequests(ownerName = ownerName,
            repoName = repoName,
            OAuthParams = OAuthParams)
            .map { response -> run {
                if(response.isEmpty()){
                   throw NoPullRequestFailure("No Open Pull Request Found")
                }
                response.mapTo(ArrayList()){ it.toPullRequestDisplayData()
            }
            }}
    }

}