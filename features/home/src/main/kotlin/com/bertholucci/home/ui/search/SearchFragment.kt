package com.bertholucci.home.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.bertholucci.domain.helper.fold
import com.bertholucci.domain.model.Show
import com.bertholucci.home.databinding.FragmentSearchBinding
import com.bertholucci.home.extensions.navProvider
import com.bertholucci.home.extensions.navigateWithAnimation
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private val navController: NavController by navProvider()
    private val viewModel: SearchViewModel by viewModel()
    private lateinit var adapter: SearchAdapter

    private val binding by lazy {
        FragmentSearchBinding.inflate(layoutInflater)
    }

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

    private fun setupUI() {
        adapter = SearchAdapter(
            onClick = { show ->
                navController.navigateWithAnimation(
                    SearchFragmentDirections.toShowDetails(show.id)
                )
            })
        binding.rvTracks.adapter = adapter
    }

    private fun addListeners() {
        binding.swipe.setOnRefreshListener {
            refreshAdapter()
        }

        binding.etSearch.addTextChangedListener {
            it?.let { text ->
                when {
                    text.isEmpty() -> adapter.setList(listOf())
                    else -> viewModel.getShowsByQuery(query = text.toString())
                }
            }
        }

        binding.error.btTryAgain.setOnClickListener {
            refreshAdapter()
        }
    }

    private fun addObservers() {
        viewModel.showsFromQuery.observe(viewLifecycleOwner) { response ->
            binding.swipe.isRefreshing = false
            response.fold(
                error = ::handleError,
                loading = { if (it) display(loading = true) },
                success = ::handleSuccess
            )
        }
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
        binding.rvTracks.isVisible = content
        binding.loading.shimmer.isVisible = loading
        binding.error.root.isVisible = error
        binding.empty.root.isVisible = empty
    }

    private fun refreshAdapter() {
        binding.etSearch.text.toString().apply {
            if (isNotEmpty()) viewModel.getShowsByQuery(this)
            else binding.swipe.isRefreshing = false
        }
    }
}