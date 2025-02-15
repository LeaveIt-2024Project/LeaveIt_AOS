package com.example.leaveit.presentation.placeview.place.showplaceview.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.leaveit.databinding.ItemRecentplacelogRecyclerviewBinding
import com.example.leaveit.local.PlaceSearch.RecentSearchPlaceEntity

class RecentPlaceLogViewAdapter(
    val deleteLogClickListener: (String) -> (Unit),
    val gotoDetailPlaceView : (String) -> (Unit)) :
    ListAdapter<RecentSearchPlaceEntity, RecentPlaceLogViewAdapter.RecentPlaceLogViewHolder>(
        diffUtil
    ) {

        inner class RecentPlaceLogViewHolder(private val binding: ItemRecentplacelogRecyclerviewBinding) :
            RecyclerView.ViewHolder(binding.root) {
            fun bind(model: RecentSearchPlaceEntity) {
                binding.root.setOnClickListener {
                    gotoDetailPlaceView(model.title)
                }
               binding.recentPlaceLogText.text = model.title

                // 클릭 리스너 설정
                binding.recentPlaceLogBtn.setOnClickListener {
                    deleteLogClickListener(model.uid) // 클릭 시 호출자(View)에 아이템의 contentTypeId 전달
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentPlaceLogViewHolder {
        return RecentPlaceLogViewHolder(
            ItemRecentplacelogRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecentPlaceLogViewHolder, position: Int) {
       holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<RecentSearchPlaceEntity>() {
            override fun areItemsTheSame(
                oldItem: RecentSearchPlaceEntity,
                newItem: RecentSearchPlaceEntity
            ): Boolean {
                return oldItem.uid == newItem.uid
            }

            override fun areContentsTheSame(
                oldItem: RecentSearchPlaceEntity,
                newItem: RecentSearchPlaceEntity
            ): Boolean {
                return oldItem.title == newItem.title
            }


        }
    }
}