package com.example.fetchproducts.data.network

import com.example.fetchproducts.data.model.Item
import retrofit2.http.GET

interface ApiService {
    @GET("hiring.json")
    suspend fun getItems(): List<Item>
}