package eu.gsegado.leaguexplorer.modules

import eu.gsegado.leaguexplorer.presentation.details.TeamDetailsPresenter
import eu.gsegado.leaguexplorer.presentation.home.HomeSearchPresenter
import org.koin.dsl.module

object PresenterModule {

    val modules = module {
        single { HomeSearchPresenter( get() , get() ) }
        single { TeamDetailsPresenter( get() ) }
    }

}