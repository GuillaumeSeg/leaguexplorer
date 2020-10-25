package eu.gsegado.leaguexplorer.domain.usecase

import eu.gsegado.leaguexplorer.domain.exception.ApiErrorHandle
import eu.gsegado.leaguexplorer.domain.repositories.LeagueRepository
import eu.gsegado.leaguexplorer.domain.usecase.base.UseCase

class GetLeaguesUseCase(
        private val leagueRepository: LeagueRepository,
        apiErrorHandle: ApiErrorHandle
) : UseCase<List<String>?, String>(apiErrorHandle) {

    override suspend fun run(params: String?): List<String>? {
        params?.let {
            return leagueRepository.getLeaguesBySport(it)
        } ?: return emptyList()
    }

}