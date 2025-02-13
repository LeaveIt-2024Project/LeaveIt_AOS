package com.example.leaveit.presentation.review.postview.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.leaveit.databinding.ItemRecentSearchRecyclerviewBinding
import com.example.leaveit.local.RecentSearch.RecentSearchEntity

class RecentSearchListAdapter(
    private val deleteRecentData: () -> Unit
) : ListAdapter<RecentSearchEntity, RecentSearchListAdapter.RecentSearchListAdapterViewHolder>(
    diffUtil
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecentSearchListAdapterViewHolder {
        return RecentSearchListAdapterViewHolder(
            ItemRecentSearchRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: RecentSearchListAdapterViewHolder,
        position: Int
    ) {
        //뷰홀더: 내가 넣고자하는 data를 실제 레이아웃의 데이터로 연결시키는 기능
        holder.bind(currentList[position])
    }

    inner class RecentSearchListAdapterViewHolder(private val binding: ItemRecentSearchRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: RecentSearchEntity) {
            binding.recentDataTextView.text = model.recentData

            binding.recentDataDeleteBtn.setOnClickListener{
                deleteRecentData()
            }

        }
    }

    //diffutil사용하려면 diffutil.callback이라는 기능을 구현해야함
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<RecentSearchEntity>() {
            override fun areItemsTheSame(oldItem: RecentSearchEntity, newItem: RecentSearchEntity): Boolean {
               return oldItem.uid == newItem.uid
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: RecentSearchEntity, newItem: RecentSearchEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}
