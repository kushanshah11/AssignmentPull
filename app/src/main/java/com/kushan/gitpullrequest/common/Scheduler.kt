package com.kushan.gitpullrequest.common

import io.reactivex.Scheduler


interface Scheduler {

    fun io() : Scheduler

    fun mainThread() : Scheduler

    fun newThread() : Scheduler

}