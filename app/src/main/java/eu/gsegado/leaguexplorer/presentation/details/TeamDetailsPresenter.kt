package eu.gsegado.leaguexplorer.presentation.details

import android.util.Log
import eu.gsegado.leaguexplorer.domain.entities.ErrorModel
import eu.gsegado.leaguexplorer.domain.entities.Team
import eu.gsegado.leaguexplorer.domain.usecase.GetTeamDetailsUseCase
import eu.gsegado.leaguexplorer.domain.usecase.base.UseCaseResponse

class TeamDetailsPresenter(private val getTeamDetailsUseCase: GetTeamDetailsUseCase) : ITeamDetails.Presenter {

    private var view: ITeamDetails.View? = null

    fun attachView(view: ITeamDetails.View) {
        this.view = view
    }

    override fun getTeamDetails(team: String) {
        getTeamDetailsUseCase.invoke(team, object : UseCaseResponse<Team?> {

            override fun onSuccess(result: Team?) {
                result?.let {
                    Log.i(TAG, "result teams : $result")
                    view?.displayTeamInfo(it)
                } ?: run {
                    view?.showInfoNoResult()
                }
            }

            override fun onError(errorModel: ErrorModel?) {
                Log.d(TAG, "error "+ errorModel?.message)
            }

        })
    }

    companion object {
        private val TAG = TeamDetailsPresenter::class.java.name
    }
}