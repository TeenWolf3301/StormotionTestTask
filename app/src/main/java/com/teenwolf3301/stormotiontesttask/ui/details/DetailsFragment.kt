package com.teenwolf3301.stormotiontesttask.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.teenwolf3301.stormotiontesttask.databinding.FragmentDetailsBinding
import com.teenwolf3301.stormotiontesttask.utility.ARG_ITEM_ID

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.itemTitle.text = "Title"
        binding.detailsVideo.apply {
            setVideoPath("https://law.duke.edu/cspd/contest/videos/Framed-Contest_Documentaries-and-You.mp4")
            start()
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(itemId: String?): DetailsFragment {
            val args = Bundle().apply {
                putSerializable(ARG_ITEM_ID, itemId)
            }
            return DetailsFragment().apply {
                arguments = args
            }
        }
    }
}