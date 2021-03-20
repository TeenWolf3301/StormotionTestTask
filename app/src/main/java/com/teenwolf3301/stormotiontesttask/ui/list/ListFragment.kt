package com.teenwolf3301.stormotiontesttask.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.nativead.NativeAd
import com.teenwolf3301.stormotiontesttask.data.MockarooData
import com.teenwolf3301.stormotiontesttask.databinding.FragmentListBinding
import com.teenwolf3301.stormotiontesttask.utility.APP_ACTIVITY
import com.teenwolf3301.stormotiontesttask.utility.NUMBER_OF_ADS
import com.teenwolf3301.stormotiontesttask.utility.Status
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ListFragment : Fragment(), MockarooListAdapter.OnItemClickListener {

    private lateinit var adLoader: AdLoader

    private val mNativeAds: MutableList<NativeAd> = mutableListOf()
    private var mRecyclerViewItems: MutableList<Any> = mutableListOf()

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
        listViewModel.list.observe(viewLifecycleOwner, { response ->
            when (response.status) {
                Status.LOADING -> {
                    binding.apply {
                        listProgressbar.visibility = View.VISIBLE
                        recyclerList.visibility = View.GONE
                    }
                }
                Status.SUCCESS -> updateUI(response.data)
                Status.ERROR -> {
                    binding.apply {
                        listProgressbar.visibility = View.GONE
                        recyclerList.visibility = View.GONE
                        Toast.makeText(context, response.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateUI(list: List<MockarooData>?) {
        mRecyclerViewItems = list?.toMutableList() ?: mutableListOf()
        loadNativeAds()
        addAdsToList()
        adapter!!.submitList(mRecyclerViewItems)
        binding.apply {
            recyclerList.adapter = adapter
            listProgressbar.visibility = View.GONE
            recyclerList.visibility = View.VISIBLE
        }
    }

    private fun addAdsToList() {
        if (mNativeAds.isNotEmpty() && mRecyclerViewItems.isNotEmpty()) {
            val offset = (mRecyclerViewItems.size / mNativeAds.size) + 1
            var index = 0
            mNativeAds.forEach {
                mRecyclerViewItems.add(index, it)
                index += offset
            }
        }
    }

    private fun loadNativeAds() {
        adLoader =
            AdLoader.Builder(APP_ACTIVITY, "ca-app-pub-3940256099942544/2247696110")
                .forNativeAd { ad ->
                    mNativeAds.add(ad)
                    if (!adLoader.isLoading) {
                        addAdsToList()
                    }
                }.withAdListener(
                    object : AdListener() {
                        override fun onAdFailedToLoad(errorCode: Int) {
                            Log.d("TAG", errorCode.toString())
                            if (!adLoader.isLoading) {
                                addAdsToList()
                            }
                        }
                    }).build()

        adLoader.loadAds(AdRequest.Builder().build(), NUMBER_OF_ADS)
    }


    override fun onItemClick(data: MockarooData) {
        val action = ListFragmentDirections.actionListFragment2ToDetailsFragment(data)
        findNavController().navigate(action)
    }
}