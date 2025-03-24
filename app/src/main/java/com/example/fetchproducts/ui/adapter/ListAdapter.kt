package com.example.fetchproducts.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchproducts.data.model.GroupedItem
import com.example.fetchproducts.databinding.RowGroupBinding

class ListAdapter(
    private var groupedList: List<GroupedItem>,
    private val onGroupClick: (GroupedItem) -> Unit
) : RecyclerView.Adapter<ListAdapter.GroupViewHolder>() {

    override fun getItemCount(): Int = groupedList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val binding = RowGroupBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GroupViewHolder(binding, onGroupClick)
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        holder.bind(groupedList[position])
    }

    fun updateData(newList: List<GroupedItem>) {
        groupedList = newList
        notifyDataSetChanged()
    }

    inner class GroupViewHolder(
        private val binding: RowGroupBinding,
        private val onGroupClick: (GroupedItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(group: GroupedItem) {
            binding.tvListId.text = "List ID: ${group.listId}"
            binding.root.setOnClickListener {
                onGroupClick(group)
            }
            binding.btnExpand.setOnClickListener {
                onGroupClick(group)
            }
        }
    }
}