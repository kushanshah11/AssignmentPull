package com.kushan.gitpullrequest.base

import androidx.annotation.StringRes

interface ActivityContract {
    fun showError(message: String, retry: (() -> Unit)? = null)
    fun notify(@StringRes message: Int)
    fun showProgress()
    fun hideProgress()
}