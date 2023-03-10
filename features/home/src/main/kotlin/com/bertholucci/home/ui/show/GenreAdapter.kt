package com.bertholucci.home.ui.show

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bertholucci.home.R
import com.bertholucci.home.databinding.ItemGenreBinding

class GenreAdapter(private val list: List<String> = listOf()) :
    RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        GenreViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_genre, parent, false)
        )

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    inner class GenreViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private var binding = ItemGenreBinding.bind(itemView)

        fun bind(item: String) {
            binding.tvGenre.text = item
        }
    }
}
