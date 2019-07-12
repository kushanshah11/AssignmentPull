package com.kushan.gitpullrequest.base

import com.kushan.gitpullrequest.network.exception.Failure


sealed class ViewStatus {
    data class SUCCESS(val message : String) : ViewStatus()
    data class FAIL(val failure : Failure) : ViewStatus()
    data class LOADING(val message : String) : ViewStatus()


    companion object {
        val LOADING = LOADING("Getting Data...")
        val SUCCESS = SUCCESS("Success")
    }

}