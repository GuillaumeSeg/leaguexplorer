package eu.gsegado.leaguexplorer.domain.repositories

import eu.gsegado.leaguexplorer.domain.entities.Team

interface TeamRepository {

    suspend fun getTeamsFromLeague(league: String): List<Team>?

    suspend fun getTeamDetails(teamName: String): Team?

}