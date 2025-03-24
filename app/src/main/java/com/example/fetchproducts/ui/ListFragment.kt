package com.example.fetchproducts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fetchproducts.R
import com.example.fetchproducts.databinding.FragmentListBinding
import com.example.fetchproducts.ui.adapter.ListAdapter
import com.example.fetchproducts.ui.viewmodel.ItemViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val viewModel: ItemViewModel by activityViewModels()
    private lateinit var adapter: ListAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = ListAdapter(emptyList()) { group ->
            val detailFragment = ListDetailFragment.newInstance(group.listId, group.items)
            parentFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.anim.slide_in_right,
                    R.anim.slide_out_left,
                    R.anim.slide_in_left,
                    R.anim.slide_out_right
                )
                .replace((requireActivity() as MainActivity).binding.container.id, detailFragment)
                .addToBackStack(null)
                .commit()
        }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@ListFragment.adapter
        }

        lifecycleScope.launch {
            viewModel.groupedItems.collectLatest { groups ->
                adapter.updateData(groups)
            }
        }
    }
}