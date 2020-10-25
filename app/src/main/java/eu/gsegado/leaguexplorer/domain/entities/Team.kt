package eu.gsegado.leaguexplorer.domain.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Team(
    @Json(name = "idTeam")           val id: String,
    @Json(name = "strTeam")          val name: String? = null,
    @Json(name = "strTeamBadge")     val badgeUrl: String? = null,
    @Json(name = "strTeamBanner")    val bannerUrl: String? = null,
    @Json(name = "strCountry")       val country: String? = null,
    @Json(name = "strDescriptionEN") val description: String? = null,
    @Json(name = "strLeague")        val league: String? = null,
    @Json(name = "strWebsite")       val website: String? = null
)