package eu.gsegado.leaguexplorer.data.remote

import eu.gsegado.leaguexplorer.domain.entities.ResponseLeagues
import eu.gsegado.leaguexplorer.domain.entities.ResponseTeams
import retrofit2.http.GET
import retrofit2.http.Query

interface SportDbService {

    @GET("search_all_teams.php")
    suspend fun getTeamsByLeague(@Query(value = "l") league: String): ResponseTeams

    @GET("searchteams.php")
    suspend fun getTeamDetails(@Query(value = "t") team: String): ResponseTeams

    @GET("search_all_leagues.php")
    suspend fun getLeaguesBySport(@Query(value = "s") sport: String): ResponseLeagues

}