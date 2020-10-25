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

    override fun displayTeamInfo(team: Team) {
        team.bannerUrl?.let {
            Glide
                .with(this)
                .load(it)
                .fitCenter()
                .into(banner)
        } ?: run {
            banner.visibility = View.GONE
        }

        team.name?.let {
            supportActionBar?.title = it
        }

        team.country?.let {
            country.text = it
        } ?: run {
            country.visibility = View.GONE
        }

        team.league?.let {
            league.text = it
        } ?: run {
            league.visibility = View.GONE
        }

        team.description?.let {
            description.text = it
        } ?: run {
            description.visibility = View.GONE
        }

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