package com.example.fetchproducts.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchproducts.data.model.GroupedItem
import com.example.fetchproducts.data.model.Item
import com.example.fetchproducts.data.repository.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val repository: ItemRepository
) : ViewModel() {

    private val _groupedItems = MutableStateFlow<List<GroupedItem>>(emptyList())
    val groupedItems: StateFlow<List<GroupedItem>> get() = _groupedItems

    init {
        fetchGroupedItems()
    }

    private fun fetchGroupedItems() {
        viewModelScope.launch {
            try {
                val filtered = repository.getFilteredItems()
                Log.d("MainViewModel", "Filtered Items: $filtered")
                // Sort the entire list based on listId and then by name
                val sortedItems = filtered.sortedWith(
                    compareBy<Item> { it.listId }.thenComparator { a, b ->
                        val (prefixA, numA) = extractParts(a.name)
                        val (prefixB, numB) = extractParts(b.name)
                        // Compare alphabetical parts and then numeric parts
                        val cmpPrefix = prefixA.compareTo(prefixB, ignoreCase = true)
                        if (cmpPrefix != 0) {
                            cmpPrefix
                        } else {
                            when {
                                numA == null && numB == null -> 0
                                numA == null -> -1
                                numB == null -> 1
                                else -> numA.compareTo(numB)
                            }
                        }
                    }
                )

                // Group by listId
                val map = sortedItems.groupBy { it.listId }
                val groups = map.map { (listId, items) ->
                    GroupedItem(
                        listId = listId,
                        items = items,
                        isExpanded = false
                    )
                }
                // Sort groups by listId
                val sortedGroups = groups.sortedBy { it.listId }
                Log.d("MainViewModel", "Grouped Items: $sortedGroups")
                _groupedItems.value = sortedGroups
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Helper function to extract the alphabetical prefix and numeric part from a name
    private fun extractParts(name: String?): Pair<String, Int?> {
        if (name == null) return Pair("", null)
        // Regex explanation:
        // ^(\D+): match one or more non-digit characters at the start (the prefix)
        // (\d+)?: optionally match one or more digits (the number)
        val regex = Regex("""^(\D+)(\d+)?""")
        val match = regex.find(name)
        return if (match != null) {
            val prefix = match.groupValues[1].trim()
            val num = match.groupValues[2].toIntOrNull()
            Pair(prefix, num)
        } else {
            Pair(name, null)
        }
    }
}