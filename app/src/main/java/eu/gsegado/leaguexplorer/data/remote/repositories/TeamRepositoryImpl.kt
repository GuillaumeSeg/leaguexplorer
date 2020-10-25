package eu.gsegado.leaguexplorer.data.remote.repositories

import eu.gsegado.leaguexplorer.data.remote.SportDbService
import eu.gsegado.leaguexplorer.domain.entities.Team
import eu.gsegado.leaguexplorer.domain.repositories.TeamRepository

class TeamRepositoryImp(private val apiService: SportDbService) : TeamRepository {

    override suspend fun getTeamsFromLeague(league: String): List<Team>? {
        return apiService.getTeamsByLeague(league).teams
    }

    override suspend fun getTeamDetails(teamName: String): Team? {
        return apiService.getTeamDetails(teamName).teams?.getOrNull(0)
    }

}