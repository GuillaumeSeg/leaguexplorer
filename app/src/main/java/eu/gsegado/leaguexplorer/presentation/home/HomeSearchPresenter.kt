package eu.gsegado.leaguexplorer.presentation.home

import android.util.Log
import eu.gsegado.leaguexplorer.domain.entities.ErrorModel
import eu.gsegado.leaguexplorer.domain.entities.Team
import eu.gsegado.leaguexplorer.domain.usecase.GetLeaguesUseCase
import eu.gsegado.leaguexplorer.domain.usecase.GetTeamsUseCase
import eu.gsegado.leaguexplorer.domain.usecase.base.UseCaseResponse
import eu.gsegado.leaguexplorer.utils.Constants

class HomeSearchPresenter(
        private val getTeamsUseCase: GetTeamsUseCase,
        private val getLeaguesUseCase: GetLeaguesUseCase
) : IHomeSearch.Presenter {

    private var view: IHomeSearch.View? = null

    fun attachView(view: IHomeSearch.View) {
        this.view = view
    }

    override fun initPresenter() {
        getLeaguesUseCase.invoke(Constants.SPORT_SOCCER, object : UseCaseResponse<List<String>?> {
            override fun onSuccess(result: List<String>?) {
                result?.let {
                    view?.setAutocompletionList(it)
                }
            }

            override fun onError(errorModel: ErrorModel?) {
                Log.d(TAG, "error "+ errorModel?.getErrorMessage())
            }
        })
    }

    override fun getTeams(league: String) {

        getTeamsUseCase.invoke(league, object : UseCaseResponse<List<Team>?> {
            override fun onSuccess(result: List<Team>?) {
                result?.let {
                    Log.i(TAG, "result teams : $result")
                    view?.setAdapterItems(result)
                } ?: run {
                    // Sometimes there are no teams associated with a league, so we can display a toast message
                    view?.showInfoNoResult()
                }
                view?.hideKeyboard()
            }

            override fun onError(errorModel: ErrorModel?) {
                Log.d(TAG, "error "+ errorModel?.getErrorMessage())
                if (errorModel?.errorStatus == ErrorModel.ErrorStatus.NO_CONNECTION) {
                    view?.showInfoNoConnection()
                }
                view?.hideKeyboard()
            }
        })
    }

    override fun selectTeam(team: Team) {
        view?.toTeamDetails(team)
    }

    companion object {
        private val TAG = HomeSearchPresenter::class.java.name
    }

}