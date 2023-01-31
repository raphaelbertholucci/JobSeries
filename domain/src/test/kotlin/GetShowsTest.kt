import com.bertholucci.domain.interactor.GetShows
import com.bertholucci.domain.model.Show
import com.bertholucci.domain.model.WeatherResultsDomain
import com.bertholucci.domain.repository.HomeRepository
import helpers.BaseTest
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class GetShowsTest : BaseTest<GetShows>() {

    @RelaxedMockK
    private lateinit var repository: HomeRepository

    override fun init() {
        agent = GetShows(repository)
    }

    @Test
    fun `get tracks by name and get the result list`() = runBlockingTest {
        coEvery { repository.getShows(any()) } returns flow {
            emit(domainResultsMock)
        }

        val request = "4418"

        agent(request)

        agent.invoke(request).collect { result ->
            assertEquals(domainResultsMock, result)
        }
    }
}

val domainMock = listOf(
    Show(
        state = "Light Cloud",
        abbr = "lc",
        minTemp = 14.4,
        maxTemp = 21.5,
        theTemp = 17.7,
        humidity = 67
    )
)

val domainResultsMock = WeatherResultsDomain(
    results = domainMock,
    title = "Toronto",
    parentTitle = "Canada"
)
