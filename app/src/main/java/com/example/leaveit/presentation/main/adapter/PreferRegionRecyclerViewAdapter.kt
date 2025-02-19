package com.example.leaveit.presentation.mainpageview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.leaveit.databinding.ItemPrefertopRecyclerviewBinding
import com.example.leaveit.presentation.mainpageview.MainPreferRegionModel

class PreferRegionRecyclerViewAdapter : ListAdapter<MainPreferRegionModel,PreferRegionRecyclerViewAdapter.PreferRegionRecyclerViewHolder>(diffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PreferRegionRecyclerViewHolder {
        return PreferRegionRecyclerViewHolder(ItemPrefertopRecyclerviewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: PreferRegionRecyclerViewHolder, position: Int) {
        //뷰홀더: 내가 넣고자하는 data를 실제 레이아웃의 데이터로 연결시키는 기능
        holder.bind(currentList[position])
    }

    inner class PreferRegionRecyclerViewHolder(private val binding : ItemPrefertopRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model : MainPreferRegionModel){
            binding.preferTop10Rank.text = model.num
            binding.preferTop10TextView.text = model.name

            Glide.with(binding.root)
                .load(model.image)
                .centerCrop()
                .into(binding.preferTop10Image)

        }
    }

    //diffutil사용하려면 diffutil.callback이라는 기능을 구현해야함
    companion object{
        val diffUtil = object: DiffUtil.ItemCallback<MainPreferRegionModel>(){
            override fun areItemsTheSame(oldItem: MainPreferRegionModel, newItem: MainPreferRegionModel): Boolean {
                return oldItem.contentId==newItem.contentId
            }

            override fun areContentsTheSame(oldItem: MainPreferRegionModel, newItem: MainPreferRegionModel): Boolean {
                return oldItem==newItem
            }


        }
    }
}