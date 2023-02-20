package com.bertholucci.home.ui

import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.testing.FragmentScenario
import com.bertholucci.domain.interactor.GetShowsByQuery
import com.bertholucci.domain.model.Image
import com.bertholucci.domain.model.Rating
import com.bertholucci.domain.model.Schedule
import com.bertholucci.domain.model.Show
import com.bertholucci.home.R
import com.bertholucci.home.extensions.checkRecyclerViewItem
import com.bertholucci.home.extensions.hasHint
import com.bertholucci.home.extensions.hasText
import com.bertholucci.home.extensions.isTextDisplayed
import com.bertholucci.home.extensions.typeText
import com.bertholucci.home.helpers.Check
import com.bertholucci.home.helpers.Execute
import com.bertholucci.home.helpers.Setup
import com.bertholucci.home.ui.search.SearchFragment
import com.bertholucci.home.ui.search.SearchViewModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun setupView(func: SearchFragmentRobot.() -> Unit) = SearchFragmentRobot().apply(func)

class SearchFragmentRobot : Setup<SearchFragmentRobotExecute, SearchFragmentRobotCheck> {

    private val useCase: GetShowsByQuery = mockk()

    init {
        startKoin {
            modules(
                listOf(
                    module { viewModel { SearchViewModel(useCase) } }
                )
            )
        }
    }

    override fun executeCreator() = SearchFragmentRobotExecute()

    override fun checkCreator() = SearchFragmentRobotCheck()

    override fun launch() {
        FragmentScenario.launchInContainer(
            fragmentClass = SearchFragment::class.java,
            fragmentArgs = null,
            themeResId = R.style.Theme_App
        )
    }

    fun mockShows() {
        coEvery {
            useCase.executeUseCase(any())
        } returns flow {
            emit(mock)
        }
    }
}

class SearchFragmentRobotExecute : Execute<SearchFragmentRobotCheck> {
    override fun checkCreator() = SearchFragmentRobotCheck()

    fun typeShow(text: String) {
        R.id.et_search.typeText(text)
    }
}

class SearchFragmentRobotCheck : Check {

    fun checkStaticViews() {
        R.id.et_search.hasHint(R.string.search_example)
        R.id.tv_hint.hasText(R.string.search_hint)
    }

    fun checkTracks() {
        checkRecyclerItem(0, "Arrow", "2013 - 2020", "50m", "6.0")
        checkRecyclerItem(1, "La Casa de Papel", "2017 - 2022", "60m", "9.0")
    }

    private fun checkRecyclerItem(
        position: Int, title: String, year: String, runtime: String, rate: String
    ) {
        R.id.rv_shows.checkRecyclerViewItem<AppCompatTextView>(position, R.id.tv_title) { view ->
            view.text == title
        }
        R.id.rv_shows.checkRecyclerViewItem<AppCompatTextView>(position, R.id.tv_year) { view ->
            view.text == year
        }
        R.id.rv_shows.checkRecyclerViewItem<AppCompatTextView>(position, R.id.tv_runtime) { view ->
            view.text == runtime
        }
        R.id.rv_shows.checkRecyclerViewItem<AppCompatTextView>(
            position, R.id.tv_rate
        ) { view ->
            view.text == rate
        }
    }
}

val mock = listOf(
    Show(
        id = 0,
        name = "Arrow",
        averageRuntime = "50",
        premiered = "2013-02-12",
        language = "English",
        genres = listOf("Action"),
        status = "Ended",
        ended = "2020-04-15",
        schedule = Schedule(time = "22:00", days = listOf("Tuesday")),
        rating = Rating(average = "6.0"),
        summary = "Once upon a time...",
        image = Image(),
        episodes = listOf()
    ), Show(
        id = 0,
        name = "La Casa de Papel",
        averageRuntime = "60",
        premiered = "2017-02-12",
        language = "English",
        genres = listOf("Action, Drama"),
        status = "Ended",
        ended = "2022-06-17",
        schedule = Schedule(time = "19:00", days = listOf("Wednesday")),
        rating = Rating(average = "9.0"),
        summary = "Once upon a time...",
        image = Image(),
        episodes = listOf()
    )
)