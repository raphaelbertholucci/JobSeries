package com.bertholucci.home.extensions

import androidx.lifecycle.MutableLiveData
import com.bertholucci.domain.helper.JobSeriesResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

fun <V> MutableLiveData<JobSeriesResponse<V>>.showLoading() {
    value = JobSeriesResponse.Loading(true)
}

fun <V> MutableLiveData<JobSeriesResponse<V>>.hideLoading() {
    value = JobSeriesResponse.Loading(false)
}

fun <V> MutableLiveData<JobSeriesResponse<V>>.success(response: V) {
    value = JobSeriesResponse.Success(response)
}

fun <V> MutableLiveData<JobSeriesResponse<V>>.failure(error: Throwable) {
    value = JobSeriesResponse.Failure(error)
}

fun <T : Any> Flow<T>.response(
    liveData: MutableLiveData<JobSeriesResponse<T>>,
    viewModelScope: CoroutineScope
) {
    this.onStart { liveData.showLoading() }
        .onCompletion { liveData.hideLoading() }
        .map { liveData.success(it) }
        .catch { liveData.failure(it) }
        .launchIn(viewModelScope)
}