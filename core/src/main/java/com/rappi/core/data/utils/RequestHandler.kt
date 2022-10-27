package com.rappi.core.data.utils

/**
 * ResponseResult for network request states.
 */
sealed class Result<out T : Any?> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

interface RemoteDataSource {
    suspend fun <T> safeApiCall(
        request: suspend () -> T,
    ): Result<T> {
        return try {
            Result.Success(request.invoke())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}