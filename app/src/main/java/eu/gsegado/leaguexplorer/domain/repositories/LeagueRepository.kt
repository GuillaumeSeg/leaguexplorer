package eu.gsegado.leaguexplorer.domain.repositories

interface LeagueRepository {

    suspend fun getLeaguesBySport(sport: String): List<String>?

}