package eu.gsegado.leaguexplorer.domain.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class League(
    @Json(name = "idLeague")  val id: String,
    @Json(name = "strLeague") val name: String,
)