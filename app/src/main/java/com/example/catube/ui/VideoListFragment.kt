package com.example.catube.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.catube.R
import com.example.catube.databinding.FragmentVideoListBinding
import com.example.catube.model.SimpleVideo

class VideoListFragment : Fragment() {
    private var _binding: FragmentVideoListBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideoListBinding.inflate(layoutInflater, container, false)

        binding.textStub.setOnClickListener {
            val direction = VideoListFragmentDirections.actionVideoListFragmentToVideoPlayerFragment(
                SimpleVideo(
                    videoUrl = "www",
                    videoTitle = "Vidosadasdasdasdad"
                )
            )
            findNavController().navigate(direction)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
