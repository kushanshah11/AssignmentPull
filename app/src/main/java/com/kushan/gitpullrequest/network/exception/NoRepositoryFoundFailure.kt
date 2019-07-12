package com.kushan.gitpullrequest.network.exception

class NoRepositoryFoundFailure(override  var message : String = "") : Failure.FeatureFailure()