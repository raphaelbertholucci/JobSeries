package com.bertholucci.home.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.bertholucci.domain.model.Show
import com.bertholucci.home.R
import com.bertholucci.home.databinding.ItemHomeBinding
import com.bertholucci.home.extensions.getAirDate
import com.bertholucci.home.extensions.ifNotEmpty
import com.bertholucci.home.extensions.loadFromUrl
import kotlin.coroutines.coroutineContext

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
            binding.apply {
                ivPoster.loadFromUrl(show.image.medium)
                tvTitle.text = show.name
                displayIfNotEmpty(textView = tvYear, text =  show.getAirDate())
                displayIfNotEmpty(textView = tvRate, text = show.rating.average)

                val runtime = show.averageRuntime.ifNotEmpty { "${show.averageRuntime}m" }
                displayIfNotEmpty(textView = tvRuntime, text = runtime)

                root.setOnClickListener {
                    onClick.invoke(show)
                }
            }
        }

        private fun displayIfNotEmpty(textView: TextView, text: String) {
            textView.isGone = text.none()
            textView.text = text
        }
    }
}