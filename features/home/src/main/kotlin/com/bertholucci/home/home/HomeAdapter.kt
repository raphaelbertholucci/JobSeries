package com.bertholucci.home.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bertholucci.domain.model.Show
import com.bertholucci.home.R
import com.bertholucci.home.ShowStatus
import com.bertholucci.home.databinding.ItemHomeBinding
import com.bertholucci.home.extensions.getAirDate
import com.bertholucci.home.extensions.getYear
import com.bertholucci.home.extensions.loadFromUrl

class HomeAdapter(
    private val shows: List<Show>,
    private val onClick: (Show) -> Unit
) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(shows[position])
    }

    override fun getItemCount() = shows.size

    inner class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding by lazy { ItemHomeBinding.bind(itemView) }

        fun bind(show: Show) {
            binding.ivPoster.loadFromUrl(show.image.medium)
            binding.tvTitle.text = show.name
            binding.tvYear.text = show.getAirDate()
            binding.tvRuntime.text = "${show.averageRuntime}m"
            binding.tvRate.text = show.rating.average

            binding.root.setOnClickListener {
                onClick.invoke(show)
            }
        }
    }
}