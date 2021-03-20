package com.teenwolf3301.stormotiontesttask.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.google.android.gms.ads.nativead.NativeAd
import com.teenwolf3301.stormotiontesttask.data.MockarooData
import com.teenwolf3301.stormotiontesttask.databinding.ListHeaderItemBinding
import com.teenwolf3301.stormotiontesttask.databinding.ListItemAdBinding
import com.teenwolf3301.stormotiontesttask.databinding.ListItemBinding
import com.teenwolf3301.stormotiontesttask.utility.TYPE_AD
import com.teenwolf3301.stormotiontesttask.utility.TYPE_HEADER
import com.teenwolf3301.stormotiontesttask.utility.TYPE_ITEM

class MockarooListAdapter(private val listener: OnItemClickListener) :

    ListAdapter<Any, RecyclerView.ViewHolder>(MockarooItemCallback()) {

    override fun getItemViewType(position: Int): Int {
        val currentItem = getItem(position)
        return if (currentItem is MockarooData) {
            if (currentItem.isHeader) TYPE_HEADER else TYPE_ITEM
        } else TYPE_AD

    }

    inner class MockarooViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null && item is MockarooData) {
                        listener.onItemClick(item)
                    }
                }
            }
        }

        fun bind(data: MockarooData) {
            binding.apply {
                listItemTitle.text = data.title
                listItemSubtitle.text = data.subtitle
                listItemImage.load(data.image) {
                    crossfade(true)
                    transformations(CircleCropTransformation())
                }
            }
        }
    }

    inner class MockarooHeaderViewHolder(private val binding: ListHeaderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: MockarooData) {
            binding.listItemHeader.text = data.header
        }
    }

    inner class AdViewHolder(private val binding: ListItemAdBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: NativeAd) {
            binding.adTitle.text = data.headline
            binding.adButton.text = data.callToAction
            binding.adImage.load(data.icon.uri)
            binding.adSubtitle.text = data.body
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> {
                val binding = ListHeaderItemBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                MockarooHeaderViewHolder(binding)
            }
            TYPE_ITEM -> {
                val binding = ListItemBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                MockarooViewHolder(binding)
            }
            else -> {
                val binding = ListItemAdBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                AdViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = getItem(position)

        when (holder.itemViewType) {
            TYPE_HEADER -> (holder as MockarooHeaderViewHolder).bind(currentItem as MockarooData)
            TYPE_AD -> (holder as AdViewHolder).bind(currentItem as NativeAd)
            TYPE_ITEM -> (holder as MockarooViewHolder).bind(currentItem as MockarooData)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(data: MockarooData)
    }
}