package com.example.leaveit.presentation.mainpageview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.leaveit.databinding.ItemMainpageRecyclerviewBinding
import com.example.leaveit.presentation.mainpageview.MainPageViewPagerItem

class MainPageViewPagerAdapter :
    RecyclerView.Adapter<MainPageViewHolder>() {

    private var items: List<MainPageViewPagerItem> = listOf()


    fun submitList(newItems: List<MainPageViewPagerItem>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainPageViewHolder {
        val binding = ItemMainpageRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainPageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainPageViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size
}

class MainPageViewHolder(private val binding: ItemMainpageRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MainPageViewPagerItem) {

        binding.mainPageTextView.text = item.title

        Glide.with(binding.mainPageImageView.context)
            .load(item.image)
            .into(binding.mainPageImageView)
    }
}
