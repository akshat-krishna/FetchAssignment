package com.example.fetchproducts.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchproducts.data.model.Item
import com.example.fetchproducts.databinding.RowItemBinding

class ListDetailAdapter(
    private var itemList: List<Item>
) : RecyclerView.Adapter<ListDetailAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(private val binding: RowItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.tvName.text = "Name: ${item.name}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = RowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    fun updateItems(newItems: ArrayList<Item>) {
        itemList = newItems
        notifyDataSetChanged()
    }
}