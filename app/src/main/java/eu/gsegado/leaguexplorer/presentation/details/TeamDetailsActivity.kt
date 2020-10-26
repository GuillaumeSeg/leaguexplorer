package eu.gsegado.leaguexplorer.presentation.details

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import eu.gsegado.leaguexplorer.R
import eu.gsegado.leaguexplorer.domain.entities.Team
import kotlinx.android.synthetic.main.activity_team_details.*
import org.koin.android.ext.android.inject

/**
 *
 * It shows the information of a team like the banner, the name, the country,
 * the league, the description and website, the user can click on the website text
 * to be redirected to the team's website in the device browser.
 */
class TeamDetailsActivity : AppCompatActivity(), ITeamDetails.View {

    private val teamDetailsPresenter: TeamDetailsPresenter by inject()

    private val teamName: String? by lazy { intent.getStringExtra(TEAM_NAME) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_team_details)

        teamDetailsPresenter.attachView(this)
        teamName?.let {
            teamDetailsPresenter.getTeamDetails(it)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // If the data is missing we can simply hide the UI.
    // Because for instance teams don't have a banner image.
    override fun displayTeamInfo(team: Team) {
        // banner image
        team.bannerUrl?.let {
            Glide
                .with(this)
                .load(it)
                .fitCenter()
                .into(banner)
        } ?: run {
            banner.visibility = View.GONE
        }

        // team name
        team.name?.let {
            supportActionBar?.title = it
        }

        // country
        team.country?.let {
            country.text = it
        } ?: run {
            country.visibility = View.GONE
        }

        // League
        team.league?.let {
            league.text = it
        } ?: run {
            league.visibility = View.GONE
        }

        // Description
        team.description?.let {
            description.text = it
        } ?: run {
            description.visibility = View.GONE
        }

        // Website
        team.website?.let { websiteSafe ->
            val content = SpannableString(websiteSafe)
            content.setSpan(UnderlineSpan(), 0, websiteSafe.length, 0)
            website.text = content
            website.setOnClickListener {
                val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://$websiteSafe"))
                try {
                    startActivity(webIntent)
                } catch (e: ActivityNotFoundException) {
                    Log.i(TAG, "website not found")
                }
            }
        } ?: run {
            description.visibility = View.GONE
        }
    }

    override fun showInfoNoResult() {
        Toast.makeText(this, R.string.team_missing, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private val TAG = TeamDetailsActivity::class.java.name
        private const val TEAM_NAME = "TEAM_NAME"

        fun start(context: Context, name: String) {
            val intent = Intent(context, TeamDetailsActivity::class.java).apply {
                putExtra(TEAM_NAME, name)
            }
            context.startActivity(intent)
        }
    }

}