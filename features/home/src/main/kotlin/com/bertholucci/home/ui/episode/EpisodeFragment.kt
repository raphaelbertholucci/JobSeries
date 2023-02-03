package com.bertholucci.home.ui.episode

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bertholucci.domain.helper.fold
import com.bertholucci.domain.model.Episode
import com.bertholucci.home.databinding.FragmentEpisodeDetailsBinding
import com.bertholucci.home.extensions.DATE_PATTERN_2
import com.bertholucci.home.extensions.getDateFormatted
import com.bertholucci.home.extensions.loadFromUrl
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class EpisodeFragment : Fragment() {

    private val args: EpisodeFragmentArgs by navArgs()
    private val viewModel: EpisodeViewModel by viewModel {
        parametersOf(args.id, args.fromFavorites)
    }

    private val binding by lazy {
        FragmentEpisodeDetailsBinding.inflate(layoutInflater)
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

    private fun addListeners() {
        binding.ivBack.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.error.btTryAgain.setOnClickListener {
            viewModel.getEpisode()
        }
    }

    private fun addObservers() {
        viewModel.episode.observe(viewLifecycleOwner) { response ->
            response.fold(
                error = ::handleError,
                loading = {
                    if (it) display(loading = true)
                },
                success = ::handleSuccess
            )
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
        Log.d("ERROR", throwable.message ?: "Error encountered on displaying the episode!")
        display(error = true)
    }

    private fun handleSuccess(episode: Episode) {
        binding.apply {
            ivEpisode.loadFromUrl(episode.image.medium)
            tvName.text = episode.name
            tvSeason.text = "Season ${episode.season} | Episode ${episode.number}"
            tvRate.text = episode.rating.average
            tvRuntime.text = "${episode.runtime}m"
            tvReleaseDate.text = episode.airDate.getDateFormatted(toPattern = DATE_PATTERN_2)
            tvSummary.text = Html.fromHtml(episode.summary, Html.FROM_HTML_MODE_COMPACT)
        }
        display(content = true)
    }
}
