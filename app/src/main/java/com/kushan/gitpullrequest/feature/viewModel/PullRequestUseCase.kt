package com.kushan.gitpullrequest.feature.viewModel

import com.kushan.gitpullrequest.common.UseCase
import com.kushan.gitpullrequest.feature.PullRequestDisplayData
import com.kushan.gitpullrequest.feature.repository.PullRequestRepo
import com.kushan.gitpullrequest.network.exception.InvalidSearchFailure
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PullRequestUseCase @Inject constructor(private val pullRequestRepository: PullRequestRepo) :
    UseCase<List<PullRequestDisplayData>, PullRequestUseCase.Params>() {


    override fun run(params: Params): Observable<List<PullRequestDisplayData>> {
        return if (params.searchQuery.contains("/")) {
            val split = params.searchQuery.split("/")
            return pullRequestRepository.fetchOpenPullRequest(split[0],split[1])
        } else {
            Observable.error(InvalidSearchFailure("Please enter valid owner and repository name."))
        }
    }


    data class Params(val searchQuery: String)
}