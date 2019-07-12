package com.kushan.gitpullrequest.feature.pullRequest

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.kushan.gitpullrequest.R
import com.kushan.gitpullrequest.base.BaseFragment
import com.kushan.gitpullrequest.base.ViewStatus
import com.kushan.gitpullrequest.common.failure
import com.kushan.gitpullrequest.common.observe
import com.kushan.gitpullrequest.common.viewModel
import com.kushan.gitpullrequest.feature.PullRequestAdapter
import com.kushan.gitpullrequest.feature.PullRequestViewModel
import com.kushan.gitpullrequest.feature.PullRequestDisplayData
import com.kushan.gitpullrequest.network.exception.InvalidSearchFailure
import com.kushan.gitpullrequest.network.exception.NoPullRequestFailure
import com.kushan.gitpullrequest.network.exception.NoRepositoryFoundFailure

import kotlinx.android.synthetic.main.fragment_pull_request.*

class PullRequestFragment : BaseFragment() {

    companion object {
        fun newInstance() = PullRequestFragment()
    }

    private lateinit var viewModel: PullRequestViewModel
    private lateinit var adapter: PullRequestAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setViewModel()
        setAdapter()
        etSearch.setupClearButtonWithAction()
        btnSearch.setOnClickListener {
            hideSoftKeyboard(etSearch)
            fetchPullRequests(etSearch.text.toString())
        }
    }

    private fun setViewModel() {
        viewModel = viewModel(viewModelFactory) {
            observe(pullRequests(), ::renderPullRequests)
            failure(getError(), ::observeError)
            failure(getError(), ::observeFeatureError)
        }
    }

    private fun observeFeatureError(viewStatus: ViewStatus?) {
        if (viewStatus is ViewStatus.FAIL) {
            when (viewStatus.failure) {
                is InvalidSearchFailure -> etSearch.error = viewStatus.failure.message
                is NoPullRequestFailure -> activityContract.showError(viewStatus.failure.message)
                is NoRepositoryFoundFailure -> {
                    rvPullRequests.visibility = View.GONE
                    activityContract.showError(viewStatus.failure.message)
                }
            }
        }
    }

    private fun fetchPullRequests(searchQuery: String) {
        viewModel.fetchPullRequests(searchQuery)
    }

    private fun setAdapter() {
        adapter = PullRequestAdapter()
        rvPullRequests.adapter = adapter
    }

    private fun renderPullRequests(pullRequests: List<PullRequestDisplayData>?) {
        pullRequests?.let {
            rvPullRequests.visibility = View.VISIBLE
            adapter.replaceAll(it)
        }
    }

    //Hide Keyboard
    private fun hideSoftKeyboard(view: View?)
    {
        try
        {
            if (view != null)
            {
                view.clearFocus()
                val inputMethodManager = view.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }
        catch (e: Exception)
        {
            e.printStackTrace()
        }
    }

    //For add cross icon in edit text
    private fun EditText.setupClearButtonWithAction() {

        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                val clearIcon = if (editable?.isNotEmpty() == true) R.drawable.ic_clear else 0
                setCompoundDrawablesWithIntrinsicBounds(0, 0, clearIcon, 0)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
        })

        setOnTouchListener(View.OnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= (this.right - this.compoundPaddingRight)) {
                    this.setText("")
                    return@OnTouchListener true
                }
            }
            return@OnTouchListener false
        })
    }


    override fun layoutId(): Int = R.layout.fragment_pull_request


}