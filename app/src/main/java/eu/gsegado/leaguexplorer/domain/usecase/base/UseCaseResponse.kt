package eu.gsegado.leaguexplorer.domain.usecase.base

import eu.gsegado.leaguexplorer.domain.entities.ErrorModel

interface UseCaseResponse<Type> {

    fun onSuccess(result: Type)

    fun onError(errorModel: ErrorModel?)

}