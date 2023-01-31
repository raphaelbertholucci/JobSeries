package com.bertholucci.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bertholucci.domain.model.Show
import com.bertholucci.home.databinding.ItemHomeBinding
import com.bertholucci.home.extensions.getYear

class HomeAdapter(private val shows: List<Show>) :
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
            binding.tvDescription.text = setupShowDescription(show)
            binding.tvRuntime.text = "${show.averageRuntime}m"
        }

        private fun setupShowDescription(show: Show): String {
            var year = show.premiered.getYear()

            if (show.status == ShowStatus.ENDED.value) {
                year = year.plus(" - ${show.ended.getYear()}")
            }
            return year
        }
    }
}