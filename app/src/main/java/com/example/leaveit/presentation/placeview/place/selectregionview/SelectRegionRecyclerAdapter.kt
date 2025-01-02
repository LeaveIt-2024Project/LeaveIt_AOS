package com.example.leaveit.presentation.placeview.place.selectregionview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.leaveit.databinding.ItemSortRecyclerviewBinding
import com.example.leaveit.presentation.placeview.place.selectregionview.data.SelectRegionModel

class SelectRegionRecyclerAdapter(
    private val moveToPlace : (data : String) -> Unit
) : ListAdapter<SelectRegionModel, SelectRegionRecyclerAdapter.SelectRegionRecyclerViewRecyclerViewHolder>(
    diffUtil
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectRegionRecyclerViewRecyclerViewHolder {
        return SelectRegionRecyclerViewRecyclerViewHolder(
            ItemSortRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: SelectRegionRecyclerViewRecyclerViewHolder,
        position: Int
    ) {
        //뷰홀더: 내가 넣고자하는 data를 실제 레이아웃의 데이터로 연결시키는 기능
        holder.bind(currentList[position])
    }

    inner class SelectRegionRecyclerViewRecyclerViewHolder(private val binding: ItemSortRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: SelectRegionModel) {
            binding.placeRegionText.text = sortRegionText(model.areaCode)

            binding.placeTitleText.text = model.title

            Glide.with(binding.root)
                .load(model.image)
                .fitCenter()
                .into(binding.imageView)

            // 클릭 리스너 설정
            binding.root.setOnClickListener {
                moveToPlace(model.contentId) // 클릭 시 호출자(View)에 아이템의 contentId 전달
            }
        }
    }

    //diffutil사용하려면 diffutil.callback이라는 기능을 구현해야함
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<SelectRegionModel>() {
            override fun areItemsTheSame(
                oldItem: SelectRegionModel,
                newItem: SelectRegionModel
            ): Boolean {
                return oldItem.contentId == newItem.contentId
            }

            override fun areContentsTheSame(
                oldItem: SelectRegionModel,
                newItem: SelectRegionModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }


    //가져온 리스트의 areaCode를 텍스트로 변환
    fun sortRegionText(number: Int) : String{
        var result : String = ""
        when(number){
            1 -> result = "서울"
            2 -> result = "인천"
            3 -> result = "대전"
            4 -> result = "대구"
            5 -> result = "광주"
            6 -> result = "부산"
            7 -> result = "울산"
            8 -> result = "세종"
            31 -> result = "경기도"
            32 -> result = "강원도"
            33 -> result = "충북"
            34 -> result = "충남"
            35 -> result = "경북"
            36 -> result = "경남"
            37 -> result = "전북"
            38 -> result = "전남"
            39 -> result = "제주"
            else -> result = "정보없음"
        }


        return result
    }
}