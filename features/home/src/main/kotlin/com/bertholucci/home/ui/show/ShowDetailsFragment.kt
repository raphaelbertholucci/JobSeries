package com.bertholucci.home.ui.show

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.navArgs
import com.bertholucci.domain.helper.fold
import com.bertholucci.domain.model.Schedule
import com.bertholucci.domain.model.Show
import com.bertholucci.home.R
import com.bertholucci.home.databinding.FragmentShowDetailsBinding
import com.bertholucci.home.extensions.getAirDate
import com.bertholucci.home.extensions.getSchedule
import com.bertholucci.home.extensions.isFavorite
import com.bertholucci.home.extensions.loadFromUrl
import com.bertholucci.home.extensions.navProvider
import com.bertholucci.home.extensions.navigateWithAnimation
import com.bertholucci.home.ui.favorites.BUNDLE_KEY_FAVORITES
import com.bertholucci.home.ui.favorites.REQUEST_KEY_FAVORITES
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ShowDetailsFragment : Fragment() {

    private val navController by navProvider()
    private val args: ShowDetailsFragmentArgs by navArgs()

    private val viewModel: ShowViewModel by viewModel {
        parametersOf(args.id, args.fromFavorites)
    }

    private val binding by lazy {
        FragmentShowDetailsBinding.inflate(layoutInflater)
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
    }

    private fun addObservers() {
        viewModel.showResponse.observe(viewLifecycleOwner) { response ->
            response.fold(
                error = ::handleError,
                loading = {
                    if (it) display(loading = true)
                },
                success = ::handleSuccess
            )
        }

        viewModel.isFavorite.observe(viewLifecycleOwner) { isFavorite ->
            binding.ivSave.isFavorite(isFavorite)
        }
    }

    private fun addListeners() {
        binding.ivBack.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.error.btTryAgain.setOnClickListener {
            viewModel.getShowFromDataSource()
        }

        binding.ivSave.setOnClickListener {
            viewModel.updateShowState()
        }
    }

    private fun display(
        content: Boolean = false,
        loading: Boolean = false,
        error: Boolean = false
    ) {
        binding.content.isVisible = content
        binding.loading.shimmer.isVisible = loading
        binding.error.root.isVisible = error
    }

    private fun handleError(throwable: Throwable) {
        Log.d("ERROR", throwable.message ?: "Error encountered on displaying the show!")
        display(error = true)
    }

    private fun handleSuccess(show: Show) {
        binding.apply {
            ivPoster.loadFromUrl(show.image.medium)
            tvName.text = show.name
            tvRate.text = show.rating.average
            tvRuntime.text = "${show.averageRuntime}m"
            tvLanguage.text = show.language
            tvReleaseDate.text = show.getAirDate()
            tvSummary.text = Html.fromHtml(show.summary, Html.FROM_HTML_MODE_COMPACT)
            setupGenreAdapter(show.genres)
            setupSchedule(show.schedule)
            setupEpisodes(show)
        }
        display(content = true)
        viewModel.updateShow(show)
    }

    private fun setupGenreAdapter(list: List<String>) {
        val layoutManager = FlexboxLayoutManager(context)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.flexWrap = FlexWrap.WRAP
        binding.rvGenre.layoutManager = layoutManager
        binding.rvGenre.adapter = GenreAdapter(list)
    }

    private fun setupSchedule(schedule: Schedule) {
        binding.tvSchedule.text = if (schedule.days.none()) {
            getString(R.string.show_schedule_none)
        } else {
            schedule.getSchedule()
        }
    }

    private fun setupEpisodes(show: Show) {
        binding.rvEpisodes.adapter = EpisodesAdapter(
            episodes = show.episodes,
            onClick = {
                navController.navigateWithAnimation(
                    ShowDetailsFragmentDirections.toEpisodeDetails(it.id)
                )
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (viewModel.hasChanged.value == true) setFragmentResult(
            REQUEST_KEY_FAVORITES, bundleOf(
                BUNDLE_KEY_FAVORITES to true
            )
        )
    }
}