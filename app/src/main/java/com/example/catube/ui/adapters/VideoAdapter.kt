package com.example.catube.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.catube.R
import com.example.catube.databinding.ItemViewBinding
import com.example.catube.model.Video
import com.example.catube.model.toSimpleVideo
import com.example.catube.ui.Navigator

class VideoAdapter(
    private val context: Context,
    private val navigator: Navigator
): RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

    private var videos: List<Video> = emptyList()

    inner class ViewHolder(private val binding: ItemViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(video: Video) {
            if (video.videoTitle.isEmpty()) {
                binding.rvVideoTitle.text = context.getString(R.string.unknown_video)
            }
            else {
                binding.rvVideoTitle.text = video.videoTitle
            }
            if (video.videoDuration == 0L) {
                binding.rvDuration.text = context.getString(R.string.unknown_duration)
            }
            else {
                val hours = video.videoDuration / 3600
                val minutes = (video.videoDuration % 3600) / 60
                val seconds = video.videoDuration % 60
                val duration = context.getString(R.string.duration_builder, hours, minutes, seconds)
                binding.rvDuration.text = duration
            }
            Glide.with(context)
                .load(video.videoCover)
                .error(R.drawable.cat_stub)
                .into(binding.rvImage)
            binding.root.setOnClickListener {
                navigator.watchVideo(video.toSimpleVideo())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemViewBinding.inflate((LayoutInflater.from(parent.context)), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(videos[position])
    }
    override fun getItemCount(): Int = videos.size

    fun submitList(newVideos: List<Video>) {
        val diffResult = DiffUtil.calculateDiff(VideoDiffCallback(videos, newVideos))
        videos = newVideos
        diffResult.dispatchUpdatesTo(this)
    }
}
