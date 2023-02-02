package com.bertholucci.data

import com.bertholucci.data.mapper.ShowMapper
import com.bertholucci.data.model.EpisodesEmbeddedResponse
import com.bertholucci.data.model.ImageResponse
import com.bertholucci.data.model.RatingResponse
import com.bertholucci.data.model.ScheduleResponse
import com.bertholucci.data.model.ShowResponse
import com.bertholucci.data.repository.ShowRepositoryImpl
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeRepositoryImplTest : BaseTest<ShowRepositoryImpl>() {

    @RelaxedMockK
    private lateinit var api: JobSeriesApi

    override fun init() {
        agent = ShowRepositoryImpl(api)
    }

    @Test
    fun `get weather information based on current location id`() = runBlockingTest {
        coEvery { api.getShowById(any()) } returns response

        agent.getShowById(0).collect {
            assertEquals(ShowMapper.mapToDomain(response), it)
        }
    }
}

val response = ShowResponse(
    id = 0,
    name = "Under the Dome",
    language = "English",
    genres = listOf("Action"),
    status = "Ended",
    averageRuntime = "60",
    premiered = "2013-10-04",
    ended = "2018-05-25",
    schedule = ScheduleResponse(
        time = "22:00",
        days = listOf("Thursdays")
    ),
    rating = RatingResponse(average = "7.5"),
    summary = "Once upon a time...",
    image = ImageResponse(medium = "test"),
    embedded = EpisodesEmbeddedResponse(listOf())
)