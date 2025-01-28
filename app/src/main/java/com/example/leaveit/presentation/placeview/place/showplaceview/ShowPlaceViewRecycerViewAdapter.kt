package com.example.leaveit.presentation.placeview.place.showplaceview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.leaveit.databinding.ItemShowplaceRecyclerviewBinding
import com.example.leaveit.presentation.placeview.place.showplaceview.DTO.CategoryDto

class ShowPlaceViewRecyclerViewAdapter(val onClick: (String) -> (Unit)) :
    ListAdapter<CategoryDto, ShowPlaceViewRecyclerViewAdapter.ShowPlaceViewRecyclerViewHolder>(
        diffUtil
    ) {
    private val multioption = MultiTransformation(RoundedCorners(36))

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
        fun bind(model: CategoryDto) {
            binding.infoTextView.text = model.title

            Glide.with(binding.root)
                .load(model.image)
                .apply(RequestOptions.bitmapTransform(multioption))
                .into(binding.infoImageView)

            // 클릭 리스너 설정
            binding.root.setOnClickListener {
                onClick(model.title) // 클릭 시 호출자(View)에 아이템의 contentId 전달
            }
        }
    }

    //diffutil사용하려면 diffutil.callback이라는 기능을 구현해야함
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<CategoryDto>() {
            override fun areItemsTheSame(
                oldItem: CategoryDto,
                newItem: CategoryDto
            ): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(
                oldItem: CategoryDto,
                newItem: CategoryDto
            ): Boolean {
                return oldItem == newItem
            }


        }
    }
}