package eu.gsegado.leaguexplorer.modules

import eu.gsegado.leaguexplorer.data.remote.SportDbService
import eu.gsegado.leaguexplorer.data.remote.repositories.LeagueRepositoryImp
import eu.gsegado.leaguexplorer.data.remote.repositories.TeamRepositoryImp
import eu.gsegado.leaguexplorer.domain.exception.ApiErrorHandle
import eu.gsegado.leaguexplorer.domain.repositories.LeagueRepository
import eu.gsegado.leaguexplorer.domain.repositories.TeamRepository
import eu.gsegado.leaguexplorer.domain.usecase.GetLeaguesUseCase
import eu.gsegado.leaguexplorer.domain.usecase.GetTeamDetailsUseCase
import eu.gsegado.leaguexplorer.domain.usecase.GetTeamsUseCase
import org.koin.dsl.module

/**
 * All relative koin modules of Use case and repositories
 */
object DomainModule {

    val modules = module {

        single { createTeamRepository(get()) }

        single { createLeagueRepository(get()) }

        single { GetTeamsUseCase(get(), ApiErrorHandle()) }

        single { GetTeamDetailsUseCase(get(), ApiErrorHandle()) }

        single { GetLeaguesUseCase(get(), ApiErrorHandle()) }
    }

    private fun createTeamRepository(apiService: SportDbService): TeamRepository {
        return TeamRepositoryImp(apiService)
    }

    private fun createLeagueRepository(apiService: SportDbService): LeagueRepository {
        return LeagueRepositoryImp(apiService)
    }
}