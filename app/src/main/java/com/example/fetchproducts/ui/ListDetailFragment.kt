package com.example.fetchproducts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fetchproducts.R
import com.example.fetchproducts.data.model.Item
import com.example.fetchproducts.databinding.FragmentListDetailBinding
import com.example.fetchproducts.ui.adapter.ListDetailAdapter

class ListDetailFragment : Fragment() {

    private lateinit var binding: FragmentListDetailBinding
    private lateinit var adapter: ListDetailAdapter

    companion object {
        private const val ARG_LIST_ID = "list_id"
        private const val ARG_ITEMS = "items"

        fun newInstance(listId: Int, items: List<Item>): ListDetailFragment {
            val fragment = ListDetailFragment()
            val bundle = Bundle().apply {
                putInt(ARG_LIST_ID, listId)
                putSerializable(ARG_ITEMS, ArrayList(items))
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentListDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val listId = arguments?.getInt(ARG_LIST_ID) ?: -1
        adapter = ListDetailAdapter(emptyList())
        binding.apply {
            toolbar.title = "List ID: $listId"
            val navIcon = androidx.appcompat.content.res.AppCompatResources.getDrawable(requireContext(), androidx.appcompat.R.drawable.abc_ic_ab_back_material)
            navIcon?.setTint(ContextCompat.getColor(requireContext(), R.color.group_background))
            toolbar.navigationIcon = navIcon
            toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = adapter
        }
        val items = arguments?.getSerializable(ARG_ITEMS) as? ArrayList<Item> ?: arrayListOf()
        adapter.updateItems(items)
    }
}