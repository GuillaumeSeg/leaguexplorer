package eu.gsegado.leaguexplorer.domain.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseTeams(
    @Json(name = "teams") val teams: List<Team>? = null,
)