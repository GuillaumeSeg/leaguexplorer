package eu.gsegado.leaguexplorer.presentation.details

import eu.gsegado.leaguexplorer.domain.entities.Team

interface ITeamDetails {

    interface View {
        fun displayTeamInfo(team: Team)

        fun showInfoNoResult()
    }

    interface Presenter {
        fun getTeamDetails(team: String)
    }

}