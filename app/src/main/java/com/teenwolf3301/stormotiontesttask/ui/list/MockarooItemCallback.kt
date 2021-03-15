package com.teenwolf3301.stormotiontesttask.ui.list

import androidx.recyclerview.widget.DiffUtil
import com.teenwolf3301.stormotiontesttask.data.MockarooData

class MockarooItemCallback : DiffUtil.ItemCallback<MockarooData>() {
    override fun areItemsTheSame(oldItem: MockarooData, newItem: MockarooData): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: MockarooData, newItem: MockarooData): Boolean =
        oldItem == newItem
}