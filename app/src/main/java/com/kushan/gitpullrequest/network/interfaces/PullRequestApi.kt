package com.kushan.gitpullrequest.network.interfaces

import com.kushan.gitpullrequest.network.responseBean.PullRequestResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface PullRequestApi {

    @GET("repos/{ownerName}/{repoName}/pulls")
    fun getPullRequests(@Path("ownerName") ownerName : String,
                        @Path("repoName") repoName : String,
                        @QueryMap OAuthParams :  HashMap<String,String>) : Observable<List<PullRequestResponse>>
}