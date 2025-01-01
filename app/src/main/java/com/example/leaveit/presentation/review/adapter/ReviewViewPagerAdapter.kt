package com.example.leaveit.presentation.review.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.leaveit.databinding.ItemReviewViewpagerBinding

class ReviewViewPagerAdapter(val image: List<String>) :
    RecyclerView.Adapter<ReviewViewPagerAdapter.ReviewViewPagerViewHolder>() {

    private val multioption = MultiTransformation(RoundedCorners(36))

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReviewViewPagerViewHolder {
        val binding = ItemReviewViewpagerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ReviewViewPagerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return image.size
    }

    override fun onBindViewHolder(holder: ReviewViewPagerViewHolder, position: Int) {
        holder.bind(image[position])
    }

    inner class ReviewViewPagerViewHolder(private val binding: ItemReviewViewpagerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val displayMetrics = binding.root.resources.displayMetrics
        val parentWidth = displayMetrics.widthPixels
        val parentHeight = displayMetrics.heightPixels / 2

        fun bind(image: String) {
            Glide.with(binding.reviewViewPagerImage)
                .load(image)
                .apply(RequestOptions.bitmapTransform(multioption))
                .override(parentWidth, parentHeight)
                .into(binding.reviewViewPagerImage)
        }
    }
}