package com.bertholucci.home.ui.favorites

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.NavController
import com.bertholucci.domain.helper.fold
import com.bertholucci.domain.model.Show
import com.bertholucci.home.R
import com.bertholucci.home.databinding.FragmentFavoritesBinding
import com.bertholucci.home.extensions.navProvider
import com.bertholucci.home.extensions.navigateWithAnimation
import com.bertholucci.home.extensions.showSortAlert
import com.bertholucci.home.ui.ShowsAdapter
import com.bertholucci.home.ui.search.SearchFragmentDirections
import org.koin.androidx.viewmodel.ext.android.viewModel

const val REQUEST_KEY_FAVORITES = "favorite_show_request"
const val BUNDLE_KEY_FAVORITES = "favorite_show_bundle"

class FavoritesFragment : Fragment() {

    private val viewModel: FavoritesViewModel by viewModel()
    private val navController: NavController by navProvider()

    private val binding by lazy {
        FragmentFavoritesBinding.inflate(layoutInflater)
    }

    private lateinit var adapter: ShowsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObservers()
        addListeners()
        setupUI()
    }

    private fun addObservers() {
        viewModel.shows.observe(viewLifecycleOwner) { response ->
            binding.swipe.isRefreshing = false
            response.fold(
                error = ::handleError,
                success = ::handleSuccess,
                loading = { if (it) display(loading = true) }
            )
        }
    }

    private fun addListeners() {
        binding.swipe.setOnRefreshListener {
            viewModel.getShows()
        }

        binding.ivBack.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.ivSort.setOnClickListener {
            context?.run {
                showSortAlert(R.array.sort_shows) { which -> viewModel.getShows(which) }
            }
        }

        setFragmentResultListener(REQUEST_KEY_FAVORITES) { _, bundle ->
            if (bundle.getBoolean(BUNDLE_KEY_FAVORITES)) viewModel.getShows()
        }
    }

    private fun setupUI() {
        adapter = ShowsAdapter(
            onClick = { show ->
                navController.navigateWithAnimation(
                    SearchFragmentDirections.toShowDetails(show.id, true)
                )
            })
        binding.rvShows.adapter = adapter
    }

    private fun handleSuccess(list: List<Show>) {
        when {
            list.isEmpty() -> display(empty = true)
            else -> {
                display(content = true)
                adapter.setList(list)
            }
        }
    }

    private fun handleError(throwable: Throwable) {
        Log.d("ERROR", throwable.message ?: "Error encountered displaying the shows!")
        display(error = true)
    }

    private fun display(
        content: Boolean = false,
        loading: Boolean = false,
        error: Boolean = false,
        empty: Boolean = false
    ) {
        binding.rvShows.isVisible = content
        binding.loading.shimmer.isVisible = loading
        binding.error.root.isVisible = error
        binding.empty.root.isVisible = empty
    }
}
