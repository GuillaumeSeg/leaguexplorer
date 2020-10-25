package eu.gsegado.leaguexplorer.domain.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseLeagues(
    @Json(name = "countrys") val leagues: List<League>? = null,
)