package com.example.catube.ui

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.fragment.navArgs
import com.example.catube.ActionBar
import com.example.catube.databinding.FragmentVideoPlayerBinding
import com.example.catube.viewmodel.VideoPlayerViewModel
import com.example.catube.viewmodel.utils.ViewModelFactory

class VideoPlayerFragment : Fragment() {
    private var _binding: FragmentVideoPlayerBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var videoPlayer: ExoPlayer

    private val viewModel: VideoPlayerViewModel by viewModels<VideoPlayerViewModel> {
        ViewModelFactory(this)
    }

    private val args: VideoPlayerFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideoPlayerBinding.inflate(layoutInflater, container, false)

        val title = args.video.videoTitle
        val videoUrl = args.video.videoUrl
        val screenOrientation = resources.configuration.orientation

        (requireActivity() as ActionBar).setTitle(title)
        binding.videoTitle.text = title

        when (screenOrientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                binding.videoTitle.isVisible = false
            }
            Configuration.ORIENTATION_PORTRAIT -> {
                binding.videoTitle.isVisible = true
            }
            else -> {
                binding.videoTitle.isVisible = true
            }
        }

        viewModel.playbackPosition.observe(viewLifecycleOwner) {
            videoPlayer.seekTo(it)
        }

        videoPlayer = ExoPlayer.Builder(requireContext()).build()
        binding.videoPlayer.player = videoPlayer
        videoPlayer.setMediaItem(MediaItem.fromUri(videoUrl))
        videoPlayer.addListener(object: Player.Listener {
            override fun onPlayerError(error: PlaybackException) {
                viewModel.setPlaybackPosition(videoPlayer.currentPosition)
                binding.videoTitle.isVisible = false
                binding.videoPlayer.isVisible = false
                binding.refreshGroup.isVisible = true
                binding.refreshTv.text = error.message
                super.onPlayerError(error)
            }
        })

        videoPlayer.prepare()
        videoPlayer.play()

        binding.refreshButton.setOnClickListener {
            videoPlayer.prepare()
            binding.videoTitle.isVisible = true
            binding.videoPlayer.isVisible = true
            binding.refreshGroup.isVisible = false
            videoPlayer.play()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        videoPlayer.release()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        videoPlayer.playWhenReady = true
    }

    override fun onStop() {
        super.onStop()
        viewModel.setPlaybackPosition(videoPlayer.currentPosition)
        videoPlayer.playWhenReady = false
    }
}
