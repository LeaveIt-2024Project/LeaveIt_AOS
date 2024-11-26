package com.example.leaveit.presentation.placeview.selectregionview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.leaveit.databinding.ItemSortRecyclerviewBinding
import com.example.leaveit.presentation.placeview.showplaceview.ShowPlaceModel

class SelectRegionRecyclerAdapter : ListAdapter<ShowPlaceModel, SelectRegionRecyclerAdapter.SelectRegionRecyclerViewRecyclerViewHolder>(
    diffUtil
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectRegionRecyclerAdapter.SelectRegionRecyclerViewRecyclerViewHolder {
        return SelectRegionRecyclerViewRecyclerViewHolder(
            ItemSortRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: SelectRegionRecyclerAdapter.SelectRegionRecyclerViewRecyclerViewHolder,
        position: Int
    ) {
        //뷰홀더: 내가 넣고자하는 data를 실제 레이아웃의 데이터로 연결시키는 기능
        holder.bind(currentList[position])
    }

    inner class SelectRegionRecyclerViewRecyclerViewHolder(private val binding: ItemSortRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: ShowPlaceModel) {

            binding.contentNameTextView.text = model.title

            Glide.with(binding.root)
                .load(model.image)
                .fitCenter()
                .into(binding.imageView)

//            // 클릭 리스너 설정
//            binding.root.setOnClickListener {
//                onClick(model.contentId) // 클릭 시 호출자(View)에 아이템의 contentId 전달
//            }
        }
    }

    //diffutil사용하려면 diffutil.callback이라는 기능을 구현해야함
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ShowPlaceModel>() {
            override fun areItemsTheSame(
                oldItem: ShowPlaceModel,
                newItem: ShowPlaceModel
            ): Boolean {
                return oldItem.contentId == newItem.contentId
            }

            override fun areContentsTheSame(
                oldItem: ShowPlaceModel,
                newItem: ShowPlaceModel
            ): Boolean {
                return oldItem == newItem
            }


        }
    }
}