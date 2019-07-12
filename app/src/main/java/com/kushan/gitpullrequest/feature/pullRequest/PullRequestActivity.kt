package com.kushan.gitpullrequest.feature.pullRequest

import com.kushan.gitpullrequest.base.BaseActivity


class PullRequestActivity : BaseActivity() {

    override fun fragment() = PullRequestFragment.newInstance()

}
