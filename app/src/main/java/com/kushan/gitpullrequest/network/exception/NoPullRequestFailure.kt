package com.kushan.gitpullrequest.network.exception

class NoPullRequestFailure(override  var message : String = "") : Failure.FeatureFailure()