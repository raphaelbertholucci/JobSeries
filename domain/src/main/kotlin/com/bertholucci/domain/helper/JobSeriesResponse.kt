package com.bertholucci.domain.helper

sealed class JobSeriesResponse<out V> {
    data class Success<out V>(val value: V) : JobSeriesResponse<V>()
    data class Failure(val error: Throwable) : JobSeriesResponse<Nothing>()
    data class Loading(val loading: Boolean) : JobSeriesResponse<Nothing>()

    private fun <V> success(value: V): JobSeriesResponse<V> = Success(value)
    private fun failure(value: Throwable): JobSeriesResponse<Nothing> = Failure(value)
    private fun loading(value: Boolean): JobSeriesResponse<Nothing> = Loading(value)

    fun <V> response(action: () -> V): JobSeriesResponse<V> =
        try {
            success(action())
        } catch (e: Exception) {
            failure(e)
        }
}

inline fun <V, A> JobSeriesResponse<V>.fold(
    error: (Throwable) -> A,
    loading: (Boolean) -> A,
    success: (V) -> A
): A = when (this) {
    is JobSeriesResponse.Failure -> error(this.error)
    is JobSeriesResponse.Success -> success(this.value)
    is JobSeriesResponse.Loading -> loading(this.loading)
}