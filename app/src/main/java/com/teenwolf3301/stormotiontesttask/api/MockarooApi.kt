package com.teenwolf3301.stormotiontesttask.api

import com.teenwolf3301.stormotiontesttask.data.MockarooData
import retrofit2.http.GET

interface MockarooApi {

    @GET("/mockaroo_data?key=da29ca90")
    suspend fun getData(): List<MockarooData>
}