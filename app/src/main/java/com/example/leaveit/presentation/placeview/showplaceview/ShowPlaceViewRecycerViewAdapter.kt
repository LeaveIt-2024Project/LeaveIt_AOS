package com.example.leaveit.presentation.placeview.showplaceview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.leaveit.databinding.ItemShowplaceRecyclerviewBinding
import com.example.leaveit.presentation.placeview.selectregionview.data.SelectRegionModel

class ShowPlaceViewRecyclerViewAdapter(val onClick: (String) -> (Unit)) :
    ListAdapter<SelectRegionModel, ShowPlaceViewRecyclerViewAdapter.ShowPlaceViewRecyclerViewHolder>(
        diffUtil
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShowPlaceViewRecyclerViewHolder {
        return ShowPlaceViewRecyclerViewHolder(
            ItemShowplaceRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ShowPlaceViewRecyclerViewHolder, position: Int) {
        //뷰홀더: 내가 넣고자하는 data를 실제 레이아웃의 데이터로 연결시키는 기능
        holder.bind(currentList[position])
    }

    inner class ShowPlaceViewRecyclerViewHolder(private val binding: ItemShowplaceRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: SelectRegionModel) {
            binding.infoTextView.text = model.title

            Glide.with(binding.root)
                .load(model.image)
                .centerCrop()
                .into(binding.infoImageView)

            // 클릭 리스너 설정
            binding.root.setOnClickListener {
                onClick(model.contentId) // 클릭 시 호출자(View)에 아이템의 contentId 전달
            }
        }
    }

    //diffutil사용하려면 diffutil.callback이라는 기능을 구현해야함
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<SelectRegionModel>() {
            override fun areItemsTheSame(
                oldItem: SelectRegionModel,
                newItem: SelectRegionModel
            ): Boolean {
                return oldItem.contentId == newItem.contentId
            }

            override fun areContentsTheSame(
                oldItem: SelectRegionModel,
                newItem: SelectRegionModel
            ): Boolean {
                return oldItem == newItem
            }


        }
    }
}