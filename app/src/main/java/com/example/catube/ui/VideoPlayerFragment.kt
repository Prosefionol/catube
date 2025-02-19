package com.example.catube.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.catube.ActionBar
import com.example.catube.databinding.FragmentVideoPlayerBinding

class VideoPlayerFragment : Fragment() {
    private var _binding: FragmentVideoPlayerBinding? = null
    private val binding
        get() = _binding!!

    private val args: VideoPlayerFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideoPlayerBinding.inflate(layoutInflater, container, false)

        val title = args.video.videoTitle
        (requireActivity() as ActionBar).setTitle(title)

        binding.musicGroup.isVisible = false
        binding.refreshButton.setOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
