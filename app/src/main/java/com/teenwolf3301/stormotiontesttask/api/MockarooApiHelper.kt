package com.teenwolf3301.stormotiontesttask.api

import com.teenwolf3301.stormotiontesttask.data.MockarooData

interface MockarooApiHelper {
    suspend fun dataList(): List<MockarooData>
}