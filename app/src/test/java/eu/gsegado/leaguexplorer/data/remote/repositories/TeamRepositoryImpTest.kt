package eu.gsegado.leaguexplorer.data.remote.repositories

import eu.gsegado.leaguexplorer.domain.entities.Team
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test

class TeamRepositoryImpTest {

    companion object {
        const val LEAGUE_1 = "French Ligue 1"
        const val TEAM_1 = "Dijon"
    }

    @MockK
    lateinit var teamRepository: TeamRepositoryImp

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun test_getTeamsFromLeague() = runBlocking {
        val teams = mockk<List<Team>>()
        every { runBlocking { teamRepository.getTeamsFromLeague(LEAGUE_1) } } returns (teams)

        val result = teamRepository.getTeamsFromLeague(LEAGUE_1)
        MatcherAssert.assertThat(
            "Received result [$result] & mocked [$teams] must be matches on each other!",
            result,
            CoreMatchers.`is`(teams)
        )
    }

    @Test
    fun test_getTeamDetails() = runBlocking {
        val team = mockk<Team>()
        every { runBlocking { teamRepository.getTeamDetails(TEAM_1) } } returns (team)

        val result = teamRepository.getTeamDetails(TEAM_1)
        MatcherAssert.assertThat(
            "Received result [$result] & mocked [$team] must be matches on each other!",
            result,
            CoreMatchers.`is`(team)
        )
    }

}