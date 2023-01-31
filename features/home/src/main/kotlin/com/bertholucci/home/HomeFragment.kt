package com.bertholucci.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.bertholucci.domain.helper.fold
import com.bertholucci.domain.model.Show
import com.bertholucci.home.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()

    private val binding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObservers()

        viewModel.getShows()
    }

    private fun addObservers() {
        viewModel.shows.observe(viewLifecycleOwner) { response ->
            response.fold(
                error = ::handleError,
                loading = { binding.loading.shimmer.isVisible = it },
                success = ::handleSuccess
            )
        }
    }

    private fun handleSuccess(list: List<Show>) {
        when {
            list.isEmpty() -> display(empty = true)
            else -> {
                display(content = true)
                binding.rvTracks.adapter = HomeAdapter(shows = list)
            }
        }
    }

    private fun handleError(throwable: Throwable) {
        Log.d("ERROR", throwable.message ?: "Error encountered displaying the shows!")
        display(error = true)
    }

    private fun display(content: Boolean = false, error: Boolean = false, empty: Boolean = false) {
        binding.rvTracks.isVisible = content
        binding.error.root.isVisible = error
        binding.empty.root.isVisible = empty
    }
}