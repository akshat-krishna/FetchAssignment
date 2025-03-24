package com.example.fetchproducts.data.repository

import com.example.fetchproducts.data.model.Item
import com.example.fetchproducts.data.network.ApiService
import javax.inject.Inject

class ItemRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getFilteredItems(): List<Item> {
        return apiService.getItems().filter { !it.name.isNullOrBlank() }
    }
}