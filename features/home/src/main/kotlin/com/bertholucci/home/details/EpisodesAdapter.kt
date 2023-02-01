package com.bertholucci.home.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bertholucci.domain.model.Episode
import com.bertholucci.home.R
import com.bertholucci.home.databinding.ItemEpisodeBinding
import com.bertholucci.home.databinding.ItemHeaderBinding
import com.bertholucci.home.extensions.loadFromUrl

class EpisodesAdapter(
    private val episodes: List<Episode>,
    private val onClick: (Episode) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val viewTypeItem = 0
    private val viewTypeHeader = 1
    private var previousSeason = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            viewTypeItem -> EpisodeViewHolder(layoutInflater(parent, R.layout.item_episode))
            else -> HeaderViewHolder(layoutInflater(parent, R.layout.item_header))
        }
    }

    private fun layoutInflater(parent: ViewGroup, layoutId: Int) =
        LayoutInflater.from(parent.context).inflate(layoutId, parent, false)


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when {
            (holder is EpisodeViewHolder) -> holder.bind(episodes[position])
            (holder is HeaderViewHolder) -> holder.bind(episodes[position].season)
        }
    }

    override fun getItemCount(): Int = episodes.size

    override fun getItemViewType(position: Int): Int {
        return when(previousSeason) {
            episodes[position].season -> viewTypeItem
            else -> {
                previousSeason = episodes[position].season
                viewTypeHeader
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return if (getItemViewType(position) == viewTypeItem) position.toLong()
        else -1
    }

    inner class EpisodeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding by lazy { ItemEpisodeBinding.bind(itemView) }

        fun bind(episode: Episode) {
            binding.ivPoster.loadFromUrl(episode.image.medium)
            binding.tvTitle.text = episode.name
            binding.tvEpisode.text = "Episode ${episode.number}"
            binding.tvRuntime.text = "${episode.runtime}m"

            binding.root.setOnClickListener {
                onClick.invoke(episode)
            }
        }
    }

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding by lazy { ItemHeaderBinding.bind(itemView) }

        fun bind(season: Int) {
            binding.tvSeason.text = "Season $season"
        }
    }
}