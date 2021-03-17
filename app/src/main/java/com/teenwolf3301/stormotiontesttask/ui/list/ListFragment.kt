package com.teenwolf3301.stormotiontesttask.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.teenwolf3301.stormotiontesttask.data.MockarooData
import com.teenwolf3301.stormotiontesttask.databinding.FragmentListBinding
import com.teenwolf3301.stormotiontesttask.utility.APP_ACTIVITY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(), MockarooListAdapter.OnItemClickListener {

    private var _binding: FragmentListBinding? = null
    private var adapter: MockarooListAdapter? = MockarooListAdapter(this)

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
        listViewModel.list.observe(viewLifecycleOwner, { list ->
            updateUI(list)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateUI(list: List<MockarooData>) {
        APP_ACTIVITY.title = "List"
        if (list.isNotEmpty()) {
            adapter!!.submitList(list)
            binding.apply {
                recyclerList.visibility = View.VISIBLE
                recyclerList.adapter = adapter
                emptyListText.visibility = View.GONE
            }
        } else {
            binding.apply {
                recyclerList.visibility = View.GONE
                emptyListText.visibility = View.VISIBLE
            }
        }
    }

    override fun onItemClick(data: MockarooData) {
        val action = ListFragmentDirections.actionListFragment2ToDetailsFragment(data)
        findNavController().navigate(action)
    }
}