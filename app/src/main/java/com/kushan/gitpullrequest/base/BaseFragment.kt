package com.kushan.gitpullrequest.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kushan.gitpullrequest.R
import com.kushan.gitpullrequest.network.exception.Failure
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    protected lateinit var activityContract: ActivityContract

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is ActivityContract){
            activityContract = context
        }else{
            throw IllegalStateException("Activity must implement the ${ActivityContract::class.java.simpleName}")
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId(), container, false)
    }

    open fun onBackPressed() {}

    abstract fun layoutId(): Int


    protected fun observeError(viewStatus: ViewStatus?) {
        return when (viewStatus) {
            is ViewStatus.FAIL -> {
                progressStatus(false)
                when (viewStatus.failure) {
                    is Failure.NetworkConnection -> showError(
                        viewStatus.failure.message,
                        viewStatus.failure.retry
                    )
                    is Failure.ServerError -> showError(viewStatus.failure.message, viewStatus.failure.retry)
                    else -> Unit
                }
            }
            is ViewStatus.LOADING -> {
                progressStatus(true)
            }
            is ViewStatus.SUCCESS -> {
                progressStatus(false)
            }
            null -> activityContract.notify(R.string.something_went_wrong)
        }
    }

    private fun showError(message: String, retry: () -> Unit) {
        activityContract.showError(message,retry)
    }



    private fun progressStatus(showLoading : Boolean) {
        if (showLoading) activityContract.showProgress() else activityContract.hideProgress()
    }

}