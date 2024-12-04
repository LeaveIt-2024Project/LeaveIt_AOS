package com.example.leaveit.presentation.review

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.leaveit.data.model.ReviewDataModel
import com.example.leaveit.databinding.ItemReviewRecyclerviewBinding

class ReviewRecyclerViewAdapter : ListAdapter<ReviewDataModel, ReviewRecyclerViewAdapter.ReviewRecyclerViewHolder>(
    diffUtil
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReviewRecyclerViewHolder {
        //리사이클러뷰에 사용할 아이템 UI 초기화
        return ReviewRecyclerViewHolder(
            ItemReviewRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ReviewRecyclerViewHolder,
        position: Int
    ) {
        //내가 넣고자하는 data를 실제 레이아웃의 데이터로 연결시키는 기능
        holder.bind(currentList[position])
    }

    inner class ReviewRecyclerViewHolder(private val binding :ItemReviewRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(data: ReviewDataModel) {
            binding.contentTextView.text = data.content
            binding.likeCountText.text = data.likeCount.toString()
            binding.starCountText.text = data.starCount.toString()

            // 리뷰 글쓴이 프로필 사진
            Glide.with(binding.root)
                .load(data.userImg)
                .fitCenter()
                .into(binding.userImg)
        }

        fun imageBind(data: ReviewDataModel,position: Int){
            val imageAdapter = ReviewViewPagerAdapter(data.feedImage)
            binding.imageViewPager.adapter = imageAdapter
        }

    }


    //diffutil사용하려면 diffutil.callback이라는 기능을 구현해야함
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ReviewDataModel>() {
            override fun areItemsTheSame(
                oldItem: ReviewDataModel,
                newItem: ReviewDataModel
            ): Boolean {
                return oldItem.feedUid == newItem.feedUid
            }

            override fun areContentsTheSame(
                oldItem: ReviewDataModel,
                newItem: ReviewDataModel
            ): Boolean {
                return oldItem.feedUid == newItem.feedUid
            }
        }
    }
}