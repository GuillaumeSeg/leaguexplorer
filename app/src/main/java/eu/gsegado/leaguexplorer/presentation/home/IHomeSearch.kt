package eu.gsegado.leaguexplorer.presentation.home

import eu.gsegado.leaguexplorer.domain.entities.Team

interface IHomeSearch {

    interface View {
        fun setAdapterItems(items: List<Team>)

        fun toTeamDetails(team: Team)

        fun hideKeyboard()

        fun showInfoNoResult()

        fun showInfoNoConnection()

        fun setAutocompletionList(items: List<String>)
    }

    interface Presenter {
        fun initPresenter()

        fun getTeams(league: String)

        fun selectTeam(team: Team)
    }
}