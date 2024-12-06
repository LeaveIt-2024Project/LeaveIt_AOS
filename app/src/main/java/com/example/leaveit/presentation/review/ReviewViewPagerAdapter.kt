package com.example.leaveit.presentation.review
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.leaveit.databinding.ItemReviewViewpagerBinding

class ReviewViewPagerAdapter(val image : List<String>) :
    RecyclerView.Adapter<ReviewViewPagerAdapter.ReviewViewPagerViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): ReviewViewPagerViewHolder {
        val binding = ItemReviewViewpagerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)

        return ReviewViewPagerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return image.size
    }

    override fun onBindViewHolder(holder: ReviewViewPagerViewHolder, position: Int) {
        holder.bind(image[position])
    }

    inner class ReviewViewPagerViewHolder(private val binding : ItemReviewViewpagerBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(image: String){
                Glide.with(binding.root.context)
                    .load(image)
                    .centerCrop()
                    .into(binding.reviewViewPagerImage)
            }
    }
}