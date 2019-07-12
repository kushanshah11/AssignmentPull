package com.kushan.gitpullrequest.feature


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kushan.gitpullrequest.base.BaseObserver
import com.kushan.gitpullrequest.base.BaseViewModel
import com.kushan.gitpullrequest.base.ViewStatus
import com.kushan.gitpullrequest.common.Scheduler
import com.kushan.gitpullrequest.feature.viewModel.PullRequestUseCase
import com.kushan.gitpullrequest.network.exception.Failure
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PullRequestViewModel @Inject constructor(private val openPullRequestUseCase: PullRequestUseCase,
                                               private val appScheduler: Scheduler
): BaseViewModel(){

    private val pullRequests = MutableLiveData<List<PullRequestDisplayData>>()

    fun pullRequests(): LiveData<List<PullRequestDisplayData>> = pullRequests

    fun fetchPullRequests(searchQuery: String) {
        viewStatus.postValue(ViewStatus.LOADING)
        openPullRequestUseCase
            .run(PullRequestUseCase.Params(searchQuery = searchQuery))
            .subscribeOn(appScheduler.io())
            .observeOn(appScheduler.mainThread())
            .subscribe(object : BaseObserver<List<PullRequestDisplayData>>(){
                override fun onFailure(failure: Failure) {
                    failure.retry = {fetchPullRequests(searchQuery)}
                    handleError(failure)
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onNext(data : List<PullRequestDisplayData>) {
                    viewStatus.postValue(ViewStatus.SUCCESS)
                    pullRequests.postValue(data)
                }

            })

    }


}