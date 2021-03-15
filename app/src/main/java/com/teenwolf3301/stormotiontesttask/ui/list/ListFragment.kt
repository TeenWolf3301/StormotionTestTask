package com.teenwolf3301.stormotiontesttask.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.teenwolf3301.stormotiontesttask.data.MockarooData
import com.teenwolf3301.stormotiontesttask.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private var adapter: MockarooListAdapter? = MockarooListAdapter()

    private val listViewModel: ListViewModel by lazy {
        ViewModelProvider(this).get(ListViewModel::class.java)
    }
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(layoutInflater, container, false)

        binding.apply {
            recyclerList.layoutManager = LinearLayoutManager(context)
            recyclerList.adapter = adapter
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        listViewModel.list.observe(viewLifecycleOwner, {
            updateUI(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateUI(data: List<MockarooData>) {
        adapter!!.submitList(data)
        binding.recyclerList.adapter = adapter
    }
}