package com.teenwolf3301.stormotiontesttask.ui.list

import androidx.recyclerview.widget.DiffUtil
import com.google.android.gms.ads.nativead.NativeAd
import com.teenwolf3301.stormotiontesttask.data.MockarooData

class MockarooItemCallback : DiffUtil.ItemCallback<Any>() {
    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        return if (oldItem is MockarooData && newItem is MockarooData) {
            oldItem.id == newItem.id
        } else if (oldItem is NativeAd && newItem is NativeAd) {
            oldItem.headline == newItem.headline
        } else false
    }


    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        return if (oldItem is MockarooData && newItem is MockarooData) {
            (oldItem as MockarooData) == (newItem as MockarooData)
        } else if (oldItem is NativeAd && newItem is NativeAd) {
            oldItem.headline == newItem.headline
        } else false
    }
}