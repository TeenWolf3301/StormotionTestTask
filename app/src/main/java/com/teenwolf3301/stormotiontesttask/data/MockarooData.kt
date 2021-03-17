package com.teenwolf3301.stormotiontesttask.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MockarooData(
    val id: String?,
    val isHeader: Boolean,
    val header: String?,
    val title: String?,
    val subtitle: String?,
    val description: String?,
    val image: String?,
    val video: String?
) : Parcelable