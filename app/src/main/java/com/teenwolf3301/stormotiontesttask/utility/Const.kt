package com.teenwolf3301.stormotiontesttask.utility

import com.teenwolf3301.stormotiontesttask.MainActivity
import com.teenwolf3301.stormotiontesttask.R
import com.teenwolf3301.stormotiontesttask.ui.details.DetailsFragment

const val BASE_URL = "https://my.api.mockaroo.com"

const val TYPE_HEADER = 1
const val TYPE_ITEM = 0

const val ARG_ITEM_ID = "item_id"

lateinit var APP_ACTIVITY: MainActivity

fun onItemClick(itemId: String?) {
    val fragment = DetailsFragment.newInstance(itemId)
    APP_ACTIVITY.supportFragmentManager
        .beginTransaction()
        .addToBackStack(null)
        .replace(R.id.frame_container, fragment)
        .commit()
}