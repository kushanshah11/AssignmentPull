package com.kushan.gitpullrequest.feature


data class PullRequestDisplayData(val name : String,
                                  val number : Int,
                                  val pullFrom : String,
                                  val mergeInto : String,
                                  val userName : String,
                                  val htmlUrl :String)