package com.kushan.gitpullrequest.base


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kushan.gitpullrequest.network.exception.Failure
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel(){
    protected  var viewStatus : MutableLiveData<ViewStatus> = MutableLiveData()
    protected var compositeDisposable = CompositeDisposable()
    fun getError() : LiveData<ViewStatus> = viewStatus

    protected fun handleError(e: Failure){
        when(e){
            is Failure.NetworkConnection -> viewStatus.postValue(ViewStatus.FAIL(e))
            is Failure.ServerError -> viewStatus.postValue(ViewStatus.FAIL(e))
            else -> viewStatus.postValue(ViewStatus.FAIL(e))
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}