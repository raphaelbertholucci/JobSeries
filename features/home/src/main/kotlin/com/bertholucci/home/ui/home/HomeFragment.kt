package com.bertholucci.home.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.bertholucci.home.databinding.FragmentHomeBinding
import com.bertholucci.home.extensions.navProvider
import com.bertholucci.home.extensions.navigateWithAnimation
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val navController: NavController by navProvider()
    private val viewModel: HomeViewModel by viewModel()
    private lateinit var adapter: HomeAdapter

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
        addListeners()
        setupUI()
        getShows()
    }

    private fun addListeners() {
        binding.swipe.setOnRefreshListener {
            adapter.refresh()
        }

        binding.ivSearch.setOnClickListener {
            navController.navigateWithAnimation(HomeFragmentDirections.toSearch())
        }

        binding.error.btTryAgain.setOnClickListener {
            adapter.refresh()
        }
    }

    private fun getShows() {
        lifecycleScope.launch {
            viewModel.shows.collectLatest {
                adapter.submitData(it)
                binding.swipe.isRefreshing = false
            }
        }
    }

    private fun setupUI() {
        adapter = HomeAdapter(
            onClick = { show ->
                navController.navigateWithAnimation(
                    HomeFragmentDirections.toShowDetails(show.id)
                )
            }
        ).apply {
            addLoadStateListener {
                binding.tvLoaded.isVisible = adapter.itemCount == 0
            }
            binding.rvShows.adapter = this
        }
    }
}