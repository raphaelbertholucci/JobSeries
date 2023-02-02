import com.bertholucci.domain.interactor.GetShowById
import com.bertholucci.domain.model.Image
import com.bertholucci.domain.model.Rating
import com.bertholucci.domain.model.Schedule
import com.bertholucci.domain.model.Show
import com.bertholucci.domain.repository.ShowRepository
import helpers.BaseTest
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class GetShowsByIdTest : BaseTest<GetShowById>() {

    @RelaxedMockK
    private lateinit var repository: ShowRepository

    override fun init() {
        agent = GetShowById(repository)
    }

    @Test
    fun `get tracks by name and get the result list`() = runBlockingTest {
        coEvery { repository.getShowById(any()) } returns flow {
            emit(mock)
        }

        val request = 1

        agent(request)

        agent.invoke(request).collect { result ->
            assertEquals(mock, result)
        }
    }
}

val mock = Show(
    id = 0,
    name = "Under the Dome",
    language = "English",
    genres = listOf("Action"),
    status = "Ended",
    averageRuntime = "60",
    premiered = "2013-10-04",
    ended = "2018-05-25",
    schedule = Schedule(
        time = "22:00",
        days = listOf("Thursdays")
    ),
    rating = Rating(average = "7.5"),
    summary = "Once upon a time...",
    image = Image(medium = "test"),
    episodes = listOf()
)