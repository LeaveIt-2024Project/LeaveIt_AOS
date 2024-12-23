package com.example.leaveit.presentation.review.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.leaveit.R
import com.example.leaveit.data.model.ReviewDataModel
import com.example.leaveit.databinding.ItemReviewRecyclerviewBinding
import com.example.leaveit.remote.entity.LikeEntitiy

class ReviewRecyclerViewAdapter (
    private val onLikeButtonClick: (LikeEntitiy,AppCompatImageView) -> Unit // 인터페이스 대신 람다 함수 사용,
) : PagingDataAdapter<ReviewDataModel, ReviewRecyclerViewAdapter.ReviewRecyclerViewHolder>(
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
        val item = getItem(position)
        //내가 넣고자하는 data를 실제 레이아웃의 데이터로 연결시키는 기능
        holder.bind(item)
        //holder.imageBind(currentList[position])
    }

    inner class ReviewRecyclerViewHolder(private val binding :ItemReviewRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ReviewDataModel?) {
            binding.contentTextView.text = data?.content
            binding.userNinckName.text = data?.nickname
            binding.placeText.text = data?.region
            binding.likeCountText.text = data?.likeCount.toString()
            binding.starCountText.text = data?.starCount.toString()

            val test = listOf(
                "http://tong.visitkorea.or.kr/cms/resource/71/2777971_image2_1.jpg",
                "http://tong.visitkorea.or.kr/cms/resource/71/2777971_image2_1.jpg",
                "http://tong.visitkorea.or.kr/cms/resource/71/2777971_image2_1.jpg"
            )

            // 리뷰 글쓴이 프로필 사진
            Glide.with(binding.root)
                .load(data?.userImage)
                .fitCenter()
                .into(binding.userImg)

            val imageAdapter = ReviewViewPagerAdapter(test)
            binding.imageViewPager.adapter = imageAdapter
            binding.reviewIndicator.attachTo(binding.imageViewPager)


            if(data?.isUserLiked == true){
                binding.likeBtn.setImageResource(R.drawable.like_btn_color)
            }else{
                binding.likeBtn.setImageResource(R.drawable.like_btn_uncolor)
            }

            // 좋아요 버튼
            binding.likeBtn.setOnClickListener {
                    val data = LikeEntitiy(
                        feedUID = data!!.feedUID,
                        userUID = "testUID",
                        kaKaoUID = data.kakaouid,
                        isUserLiked = data.isUserLiked
                    )
                onLikeButtonClick(data,binding.likeBtn)
            }
        }

        // 뷰페이저 어댑터 초기화
//        fun imageBind(data: ReviewDataModel){
//
//            val imageAdapter = ReviewViewPagerAdapter(test)
//            binding.imageViewPager.adapter = imageAdapter
//        }

    }


    //diffutil사용하려면 diffutil.callback이라는 기능을 구현해야함
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ReviewDataModel>() {
            override fun areItemsTheSame(
                oldItem: ReviewDataModel,
                newItem: ReviewDataModel
            ): Boolean {
                return oldItem.feedUID == newItem.feedUID
            }

            override fun areContentsTheSame(
                oldItem: ReviewDataModel,
                newItem: ReviewDataModel
            ): Boolean {
                return oldItem.feedUID == newItem.feedUID
            }
        }

        val TAG = "ReviewRecyclerViewAdapter"
    }
}