package eu.gsegado.leaguexplorer.presentation.home

import android.os.Bundle
import android.view.Menu
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import eu.gsegado.leaguexplorer.R
import eu.gsegado.leaguexplorer.domain.entities.Team
import eu.gsegado.leaguexplorer.presentation.details.TeamDetailsActivity
import eu.gsegado.leaguexplorer.utils.Utils
import kotlinx.android.synthetic.main.activity_home_search.*
import org.koin.android.ext.android.inject

/**
 *
 * The Home page, the user can search a league in the appbar and then it displays all the teams
 * in the league, the user can click on a team and then it starts another activity to display the
 * team details.
 */
class HomeSearchActivity : AppCompatActivity(), IHomeSearch.View {

    private val homeSearchPresenter: HomeSearchPresenter by inject()
    private var adapter: TeamsAdapter? = null
    private var adapterAutocompletion: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home_search)
        homeSearchPresenter.attachView(this)
        homeSearchPresenter.initPresenter()

        initTeamAdapter()
    }

    // Create the search interface with autocompletion
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val menuItem = menu?.findItem(R.id.search)
        val searchView = menuItem?.actionView as SearchView
        searchView.queryHint = getString(R.string.hint_search)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    homeSearchPresenter.getTeams(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter?.clear()
                return true
            }

        })

        val searchAutoComplete = searchView.findViewById<SearchView.SearchAutoComplete>(androidx.appcompat.R.id.search_src_text)
        adapterAutocompletion = ArrayAdapter<String>(applicationContext, R.layout.autocomplete_item, mutableListOf())
        searchAutoComplete.setAdapter(adapterAutocompletion)
        searchAutoComplete.setOnItemClickListener { _, _, position, _ ->
            adapterAutocompletion?.getItem(position)?.let {
                homeSearchPresenter.getTeams(it)
                searchView.setQuery(it, false)
            }
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun setAdapterItems(items: List<Team>) {
        adapter?.setItems(items)
    }

    override fun toTeamDetails(team: Team) {
        team.name?.let {
            TeamDetailsActivity.start(this, it)
        } ?: Toast.makeText(this, R.string.team_missing, Toast.LENGTH_SHORT).show()
    }

    override fun hideKeyboard() {
        Utils.hideSoftKeyboard(this)
    }

    override fun showInfoNoResult() {
        Toast.makeText(this, R.string.no_result, Toast.LENGTH_LONG).show()
    }

    override fun showInfoNoConnection() {
        Toast.makeText(this, R.string.no_connection_error, Toast.LENGTH_LONG).show()
    }

    override fun setAutocompletionList(items: List<String>) {
        adapterAutocompletion?.addAll(items)
    }

    private fun initTeamAdapter() {
        adapter = TeamsAdapter {
            homeSearchPresenter.selectTeam(it)
        }
        list_teams.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL,false)
        list_teams.adapter = adapter
    }

}