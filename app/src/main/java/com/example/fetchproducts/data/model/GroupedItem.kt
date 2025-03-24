package com.example.fetchproducts.data.model

data class GroupedItem(
    val listId: Int,
    val items: List<Item>,
    var isExpanded: Boolean = false
)