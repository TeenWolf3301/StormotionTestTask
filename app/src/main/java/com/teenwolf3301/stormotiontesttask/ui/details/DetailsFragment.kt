package com.teenwolf3301.stormotiontesttask.ui.details

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.CircleCropTransformation
import com.teenwolf3301.stormotiontesttask.R
import com.teenwolf3301.stormotiontesttask.databinding.FragmentDetailsBinding
import com.teenwolf3301.stormotiontesttask.utility.APP_ACTIVITY

class DetailsFragment : Fragment() {

    private val args by navArgs<DetailsFragmentArgs>()

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

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
        updateUI()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.details_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings -> {
                Toast.makeText(context, "Settings", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateUI() {
        val item = args.data
        binding.apply {
            itemTitle.text = item.title
            itemSubtitle.text = item.subtitle
            detailsDescription.text = item.description
            itemImage.load(item.image) {
                crossfade(true)
                transformations(CircleCropTransformation())
            }
            progressVideo.visibility = View.VISIBLE
            detailsVideo.apply {
                setVideoPath(item.video)
                setOnTouchListener { v, _ ->
                    if (detailsVideo.isPlaying) {
                        v.performClick()
                        detailsVideo.pause()
                    } else {
                        v.performClick()
                        detailsVideo.start()
                    }
                    return@setOnTouchListener false
                }
                setOnPreparedListener {
                    start()
                    it.isLooping = true
                    progressVideo.visibility = View.GONE
                }
            }
        }
        APP_ACTIVITY.supportActionBar?.title = item.title
    }
}