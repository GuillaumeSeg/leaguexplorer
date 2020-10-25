package eu.gsegado.leaguexplorer.domain.usecase.base

import android.util.Log
import eu.gsegado.leaguexplorer.domain.exception.ApiErrorHandle
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class UseCase<Type, in String>(private val apiErrorHandle: ApiErrorHandle) where Type : Any? {

    abstract suspend fun run(params: String? = null): Type

    fun invoke(
        params: String?,
        onResult: (UseCaseResponse<Type>)?
    ) {

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val result = run(params)
                onResult?.onSuccess(result)
                Log.d(TAG, "Response: $result")
            } catch (e: CancellationException) {
                Log.d(TAG, "Error: $e")
                onResult?.onError(apiErrorHandle.traceErrorException(e))
            } catch (e: Exception) {
                Log.d(TAG, "Error: $e cause: ${e.cause}")
                onResult?.onError(apiErrorHandle.traceErrorException(e))
            }
        }
    }

    companion object {
        private val TAG = UseCase::class.java.name
    }

}