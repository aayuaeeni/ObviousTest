package com.raju.obvioustest.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.raju.obvioustest.databinding.ActivityHomeBinding
import com.raju.obvioustest.databinding.FooterLayoutBinding
import com.raju.obvioustest.databinding.ImageListItemBinding
import com.raju.obvioustest.model.NasaPictureModel
import com.raju.obvioustest.utils.AppConstants.FINISHED
import com.raju.obvioustest.utils.AppConstants.IDLE
import com.raju.obvioustest.utils.ScreenUtils
import com.raju.obvioustest.utils.loadImageFromUrl


class HomeListAdapter(
    var pictureList: List<NasaPictureModel>,
    val listener: HomeListAdapterListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var binding: ImageListItemBinding

    private val FOOTER = 1
    private val ITEM = 0
    var pageState = IDLE

    inner class ItemViewHolder(binding: ImageListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            val containerHeight = (ScreenUtils.getScreenWidth() - ScreenUtils.convertDpToPx(24f)) / 2
            binding.rlContainer.layoutParams.height = containerHeight
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == ITEM) {
            binding = ImageListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ItemViewHolder(binding)
        } else {
            val binding =
                FooterLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            FooterViewHolder(binding)
        }
    }

    inner class FooterViewHolder(binding: FooterLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun getItemViewType(position: Int): Int {
        return if (position == pictureList.size) {
            FOOTER
        } else ITEM
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder.itemViewType == ITEM) {
            val picture = pictureList[position]
            binding.ivImage.loadImageFromUrl(picture.url)
            binding.ivImage.setOnClickListener {
                listener.onItemClicked(picture,position)
            }
        } else if (viewHolder.itemViewType == FOOTER) {
            //Do nothing
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        if (pictureList.isEmpty()) {
            return 0
        }
        return if (pageState == FINISHED) pictureList.size else pictureList.size + 1
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)
        if (holder is ItemViewHolder){
            binding.ivImage.setImageResource(0)
        }
    }
    interface HomeListAdapterListener {
        fun onItemClicked(nasaPictureModel: NasaPictureModel, position: Int)
    }
}