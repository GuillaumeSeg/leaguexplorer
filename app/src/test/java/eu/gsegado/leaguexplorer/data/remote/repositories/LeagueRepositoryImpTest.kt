package eu.gsegado.leaguexplorer.data.remote.repositories

import eu.gsegado.leaguexplorer.utils.Constants
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test

class LeagueRepositoryImpTest {

    @MockK
    lateinit var leagueRepository: LeagueRepositoryImp

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun test_getLeaguesBySportSoccer() = runBlocking {
        val leagues = mockk<List<String>>()
        every { runBlocking { leagueRepository.getLeaguesBySport(Constants.SPORT_SOCCER) } } returns (leagues)

        val result = leagueRepository.getLeaguesBySport(Constants.SPORT_SOCCER)
        MatcherAssert.assertThat(
            "Received result [$result] & mocked [$leagues] must be matches on each other!",
            result,
            CoreMatchers.`is`(leagues)
        )
    }

}