package com.bertholucci.data

import com.bertholucci.data.model.ShowResponse
import com.bertholucci.data.repository.ShowRepositoryImpl
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
        coEvery { api.getShows(any()) } returns weatherListMock

        agent.getShows("4418").collect {
            assertEquals(
                WeatherResponseResultsMapper.mapToDomain(weatherListMock),
                it
            )
        }
    }
}

val weatherListMock = ShowsResultsResponse(
    results = listOf(
        ShowResponse(
            id = 123456789,
            state = "Light Cloud",
            abbr = "lc",
            minTemp = 14.4,
            maxTemp = 21.5,
            theTemp = 17.7,
            humidity = 67
        )
    ),
    title = "Toronto",
    parent = Parent(
        title = "Canada"
    )
)