package eu.gsegado.leaguexplorer.domain.usecase

import eu.gsegado.leaguexplorer.domain.entities.Team
import eu.gsegado.leaguexplorer.domain.exception.ApiErrorHandle
import eu.gsegado.leaguexplorer.domain.repositories.TeamRepository
import eu.gsegado.leaguexplorer.domain.usecase.base.UseCase

class GetTeamDetailsUseCase constructor(
    private val teamRepository: TeamRepository,
    apiErrorHandle: ApiErrorHandle
) : UseCase<Team?, String?>(apiErrorHandle) {

    override suspend fun run(params: String?): Team? {
        params?.let {
            return teamRepository.getTeamDetails(it)
        } ?: return null
    }

}