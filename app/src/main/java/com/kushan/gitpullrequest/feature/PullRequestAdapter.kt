package com.kushan.gitpullrequest.feature

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kushan.gitpullrequest.R
import com.kushan.gitpullrequest.base.BaseRecyclerAdapter
import com.kushan.gitpullrequest.databinding.RowPullRequestBinding
import kotlinx.android.synthetic.main.row_pull_request.view.*


class PullRequestAdapter : BaseRecyclerAdapter<PullRequestDisplayData>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =  RowPullRequestBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: BaseRecyclerAdapter.ViewHolder<PullRequestDisplayData>, position: Int) {
        super.onBindViewHolder(holder, position)

        if(position % 2 == 0){
            holder.itemView.cLParent.setBackgroundResource(R.drawable.bg_gradient_primary)
        }else{
            holder.itemView.cLParent.setBackgroundResource(R.drawable.bg_gradient_secondary)
        }

    }

    class  ViewHolder(private val binding : RowPullRequestBinding) : BaseRecyclerAdapter.ViewHolder<PullRequestDisplayData>(binding){
        override fun bind(displayData : PullRequestDisplayData) {
            binding.data = displayData
        }
    }
}
