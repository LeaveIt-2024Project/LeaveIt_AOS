package com.example.leaveit.presentation.placeview.place.showplaceview.recyclerview

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.leaveit.databinding.ItemHotkeywordPlaceRecyclerviewBinding
import com.example.leaveit.domain.model.HotKeyWordDomain

class HotKeyWorldViewAdapter(val onClick : (String) -> (Unit)
) : ListAdapter<HotKeyWordDomain, HotKeyWorldViewAdapter.HotKeyWorldViewHolder>(
    diffUtil
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HotKeyWorldViewAdapter.HotKeyWorldViewHolder {
        return HotKeyWorldViewHolder(
            ItemHotkeywordPlaceRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: HotKeyWorldViewHolder,
        position: Int
    ) {
        holder.bind(currentList[position])
    }

    inner class HotKeyWorldViewHolder(private val binding : ItemHotkeywordPlaceRecyclerviewBinding)
        : RecyclerView.ViewHolder(binding.root){
            fun bind(model : HotKeyWordDomain){
                binding.root.setOnClickListener {
                    onClick(model.title)
                }

                if(model.number < 4){
                    binding.titleText.setTextColor(Color.parseColor("#31511E"))
                    binding.numberText.setTextColor(Color.parseColor("#31511E"))
                }
                binding.titleText.text = model.title
                binding.numberText.text ="${model.number}."
            }

    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<HotKeyWordDomain>() {
            override fun areItemsTheSame(
                oldItem: HotKeyWordDomain,
                newItem: HotKeyWordDomain
            ): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(
                oldItem: HotKeyWordDomain,
                newItem: HotKeyWordDomain
            ): Boolean {
                return oldItem.number == newItem.number
            }


        }
    }

}