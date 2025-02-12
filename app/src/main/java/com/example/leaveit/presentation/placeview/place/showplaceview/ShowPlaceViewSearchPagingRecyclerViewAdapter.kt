package com.example.leaveit.presentation.placeview.place.showplaceview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.leaveit.R
import com.example.leaveit.databinding.ItemSortRecyclerviewBinding
import com.example.leaveit.domain.model.PlaceDomainModel

class ShowPlaceViewSearchPagingRecyclerViewAdapter(
    val onClick: (
        String,
        String,
        String,
        String,
        String,
        String,
        String
    ) -> (Unit)
) : PagingDataAdapter<PlaceDomainModel, ShowPlaceViewSearchPagingRecyclerViewAdapter.ShowPlaceViewRecyclerViewHolder>(
    diffUtil
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShowPlaceViewSearchPagingRecyclerViewAdapter.ShowPlaceViewRecyclerViewHolder {
        return ShowPlaceViewRecyclerViewHolder(

            // 정의한 아이템 뷰에 inflate
            ItemSortRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(
        holder: ShowPlaceViewSearchPagingRecyclerViewAdapter.ShowPlaceViewRecyclerViewHolder,
        position: Int
    ) {
        //뷰홀더: 내가 넣고자하는 data를 실제 레이아웃의 데이터로 연결시키는 기능
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    inner class ShowPlaceViewRecyclerViewHolder(private val binding: ItemSortRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: PlaceDomainModel) {
            binding.placeTitleText.text = model.title

            Glide.with(binding.root)
                .load(model.image)
                .fitCenter()
                .error(R.drawable.null_image)
                .into(binding.imageView)

            // 클릭 리스너 설정
            binding.root.setOnClickListener {
                onClick(
                    model.contenttypeid,
                    model.contentId,
                    model.mapx,
                    model.mapy,
                    model.image,
                    model.addr,
                    model.title
                ) // 클릭 시 호출자(View)에 아이템의 contentTypeId 전달
            }
        }
    }

    //diffutil사용하려면 diffutil.callback이라는 기능을 구현해야함
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<PlaceDomainModel>() {
            override fun areItemsTheSame(
                oldItem: PlaceDomainModel,
                newItem: PlaceDomainModel
            ): Boolean {
                return oldItem.contentId == newItem.contentId
            }

            override fun areContentsTheSame(
                oldItem: PlaceDomainModel,
                newItem: PlaceDomainModel
            ): Boolean {
                return oldItem == newItem
            }


        }
    }

}