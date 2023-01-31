package com.bertholucci.home.details

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bertholucci.domain.helper.fold
import com.bertholucci.domain.model.Schedule
import com.bertholucci.domain.model.Show
import com.bertholucci.home.databinding.FragmentShowDetailsBinding
import com.bertholucci.home.extensions.getAirDate
import com.bertholucci.home.extensions.getSchedule
import com.bertholucci.home.extensions.gone
import com.bertholucci.home.extensions.loadFromUrl
import com.bertholucci.home.extensions.navProvider
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ShowDetailsFragment : Fragment() {

    private val navController by navProvider()
    private val args: ShowDetailsFragmentArgs by navArgs()
    private val viewModel: ShowDetailsViewModel by viewModel {
        parametersOf(args.id)
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
        viewModel.show.observe(viewLifecycleOwner) { response ->
            response.fold(
                error = ::handleError,
                loading = {},
                success = ::handleSuccess
            )
        }
    }

    private fun addListeners() {
        binding.ivBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun handleError(throwable: Throwable) {

    }

    private fun handleSuccess(show: Show) {
        binding.apply {
            ivPoster.loadFromUrl(show.image.medium)
            tvName.text = show.name
            tvRate.text = show.rating.average
            tvRuntime.text = "${show.averageRuntime}m"
            tvLanguage.text = show.language
            tvReleaseDate.text = show.getAirDate()
            setupGenreAdapter(show.genres)
            setupSchedule(show.schedule)
            tvSummary.text = Html.fromHtml(show.summary, Html.FROM_HTML_MODE_COMPACT)
        }
    }

    private fun setupGenreAdapter(list: List<String>) {
        val layoutManager = FlexboxLayoutManager(context)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.flexWrap = FlexWrap.WRAP
        binding.rvGenre.layoutManager = layoutManager
        binding.rvGenre.adapter = GenreAdapter(list)
    }

    private fun setupSchedule(schedule: Schedule) {
        if (schedule.days.none()) {
            binding.tvSchedule.gone()
            binding.tvScheduleLabel.gone()
            return
        }

        binding.tvSchedule.text = schedule.getSchedule()
    }
}