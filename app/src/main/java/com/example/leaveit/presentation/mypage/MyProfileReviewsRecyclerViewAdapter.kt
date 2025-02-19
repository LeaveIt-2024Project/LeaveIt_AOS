package com.example.leaveit.presentation.myprofile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.leaveit.databinding.ItemMyprofilereviewsRecyclerviewBinding
import com.example.leaveit.presentation.myprofile.MyProfilePageModel

class MyProfileReviewsRecyclerViewAdapter : ListAdapter<MyProfilePageModel,MyProfileReviewsRecyclerViewAdapter.MyProfileReviewsRecyclerViewHolder>(diffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyProfileReviewsRecyclerViewHolder {
        return MyProfileReviewsRecyclerViewHolder(ItemMyprofilereviewsRecyclerviewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyProfileReviewsRecyclerViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class MyProfileReviewsRecyclerViewHolder(private val binding : ItemMyprofilereviewsRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model : MyProfilePageModel){

            Glide.with(binding.root)
                .load(model.feedImage)
                .centerCrop()
                .into(binding.myProfileImgView)

        }
    }

    companion object{
        val diffUtil = object: DiffUtil.ItemCallback<MyProfilePageModel>(){
            override fun areItemsTheSame(oldItem: MyProfilePageModel, newItem: MyProfilePageModel): Boolean {
                return oldItem.feedUID==newItem.feedUID
            }
            override fun areContentsTheSame(oldItem: MyProfilePageModel, newItem: MyProfilePageModel): Boolean {
                return oldItem==newItem
            }
        }
    }
}