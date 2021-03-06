package com.teenwolf3301.stormotiontesttask.data

import com.teenwolf3301.stormotiontesttask.api.MockarooApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MockarooRepository @Inject constructor(private val mockarooApi: MockarooApi) {

    suspend fun getData() = mockarooApi.getData()

}