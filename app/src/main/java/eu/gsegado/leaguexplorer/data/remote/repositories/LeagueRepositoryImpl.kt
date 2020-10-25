package eu.gsegado.leaguexplorer.data.remote.repositories

import eu.gsegado.leaguexplorer.data.remote.SportDbService
import eu.gsegado.leaguexplorer.domain.repositories.LeagueRepository

class LeagueRepositoryImp(private val apiService: SportDbService) : LeagueRepository {

    override suspend fun getLeaguesBySport(sport: String): List<String>? {
        return apiService.getLeaguesBySport(sport).leagues?.map {
            it.name
        } ?: emptyList()
    }

}