package com.kushan.gitpullrequest.common

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.kushan.gitpullrequest.base.ViewStatus


fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) =
        liveData.observe(this, Observer(body))

fun <L : LiveData<ViewStatus>> LifecycleOwner.failure(liveData: L, body: (ViewStatus?) -> Unit) =
        liveData.observe(this, Observer(body))