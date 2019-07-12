package com.kushan.gitpullrequest.base


import com.google.gson.Gson
import com.kushan.gitpullrequest.network.exception.Failure
import com.kushan.gitpullrequest.network.exception.NoRepositoryFoundFailure
import com.kushan.gitpullrequest.network.responseBean.ErrorResponse
import io.reactivex.Observer
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

abstract class BaseObserver<T> : Observer<T> {

    override fun onError(e: Throwable) {
        e.printStackTrace()
        when (e) {

            is HttpException -> {
                if(e.code() == 404){
                    val errorResponse = e.response().errorBody()?.string()
                    try {
                        val response = Gson().fromJson<ErrorResponse>(errorResponse, ErrorResponse::class.java)
                        onFailure(NoRepositoryFoundFailure(response.message))
                    }catch (e : Exception){
                        onFailure(Failure.ServerError("Oops,something went wrong. Please try again."))
                    }
                }
            }
            is IOException -> onFailure(Failure.NetworkConnection("Make sure your device is connected to the internet"))
            is Failure -> onFailure(e)
            else -> onFailure(Failure.ServerError("Oops,something went wrong. Please try again."))
        }
    }


    override fun onComplete() {}


    abstract fun onFailure(failure: Failure)


}
