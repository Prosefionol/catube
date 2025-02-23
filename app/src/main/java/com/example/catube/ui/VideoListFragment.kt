package com.example.catube.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catube.databinding.FragmentVideoListBinding
import com.example.catube.model.SimpleVideo
import com.example.catube.model.api.ErrorAnswerApi
import com.example.catube.model.api.PendingAnswerApi
import com.example.catube.model.api.SuccessAnswerApi
import com.example.catube.model.api.getAnswer
import com.example.catube.ui.adapters.VideoAdapter
import com.example.catube.viewmodel.VideoListViewModel
import com.example.catube.viewmodel.utils.ViewModelFactory

class VideoListFragment : Fragment(), Navigator {
    private var _binding: FragmentVideoListBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: VideoListViewModel by viewModels<VideoListViewModel> {
        ViewModelFactory(this)
    }

    private lateinit var adapter: VideoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideoListBinding.inflate(layoutInflater, container, false)

        adapter = VideoAdapter(requireContext(), this)
        binding.rv.adapter = adapter
        binding.rv.layoutManager = LinearLayoutManager(requireContext())

        viewModel.videoData.observe(viewLifecycleOwner) {
            when (it) {
                is PendingAnswerApi -> {
                    binding.swipeLayout.isRefreshing = true
                    binding.rv.isVisible = false
                    binding.textStub.isVisible = false
                }
                is ErrorAnswerApi -> {
                    binding.swipeLayout.isRefreshing = false
                    binding.rv.isVisible = false
                    binding.textStub.isVisible = true
                }
                is SuccessAnswerApi -> {
                    adapter.submitList(it.getAnswer()!!)
                    binding.swipeLayout.isRefreshing = false
                    binding.rv.isVisible = true
                    binding.textStub.isVisible = false
                }
            }
        }

        binding.swipeLayout.setOnRefreshListener {
            viewModel.fetchData()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (viewModel.videoData.value == null) {
            viewModel.fetchData()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun watchVideo(simpleVideo: SimpleVideo) {
        val direction = VideoListFragmentDirections.actionVideoListFragmentToVideoPlayerFragment(
            simpleVideo
        )
        findNavController().navigate(direction)
    }
}
