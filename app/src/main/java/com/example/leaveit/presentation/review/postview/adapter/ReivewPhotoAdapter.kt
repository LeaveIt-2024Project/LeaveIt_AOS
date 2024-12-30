package com.example.leaveit.presentation.review.postview.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.leaveit.databinding.ItemPhotoReviewViewpagerBinding

class ReivewPhotoAdapter() : ListAdapter<Uri, ReivewPhotoAdapter.SelectPlaceAdapterViewHolder>(
    diffUtil
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectPlaceAdapterViewHolder {
        return SelectPlaceAdapterViewHolder(
            ItemPhotoReviewViewpagerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: SelectPlaceAdapterViewHolder,
        position: Int
    ) {
        //뷰홀더: 내가 넣고자하는 data를 실제 레이아웃의 데이터로 연결시키는 기능
        holder.bind(currentList[position])
    }

    inner class SelectPlaceAdapterViewHolder(private val binding: ItemPhotoReviewViewpagerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Uri) {
            Glide.with(binding.root)
                .load(model)
                .centerCrop()
                .into(binding.photoReviewImageView)
        }
    }

    //diffutil사용하려면 diffutil.callback이라는 기능을 구현해야함
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Uri>() {

            override fun areItemsTheSame(oldItem: Uri, newItem: Uri): Boolean {
                return oldItem.path == newItem.path
            }

            override fun areContentsTheSame(oldItem: Uri, newItem: Uri): Boolean {
                return oldItem.path == newItem.path
            }
        }
    }

    fun removePhotoList(){
        submitList(null)
    }

}