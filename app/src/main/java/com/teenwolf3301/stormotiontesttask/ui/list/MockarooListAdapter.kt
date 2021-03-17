package com.teenwolf3301.stormotiontesttask.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.teenwolf3301.stormotiontesttask.data.MockarooData
import com.teenwolf3301.stormotiontesttask.databinding.ListHeaderItemBinding
import com.teenwolf3301.stormotiontesttask.databinding.ListItemBinding
import com.teenwolf3301.stormotiontesttask.utility.TYPE_HEADER
import com.teenwolf3301.stormotiontesttask.utility.TYPE_ITEM

class MockarooListAdapter(private val listener: OnItemClickListener) :

    ListAdapter<MockarooData, RecyclerView.ViewHolder>(MockarooItemCallback()) {

    override fun getItemViewType(position: Int): Int =
        if (getItem(position).isHeader) TYPE_HEADER else TYPE_ITEM

    inner class MockarooViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_HEADER) {
            val binding =
                ListHeaderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            MockarooHeaderViewHolder(binding)
        } else {
            val binding =
                ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            MockarooViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            if (currentItem.isHeader) {
                (holder as MockarooHeaderViewHolder).bind(currentItem)
            } else {
                (holder as MockarooViewHolder).bind(currentItem)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(data: MockarooData)
    }
}