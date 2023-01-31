package com.bertholucci.home.extensions

import androidx.lifecycle.MutableLiveData
import com.bertholucci.domain.helper.JobSeriesResponse

fun <T : Any> MutableLiveData<T>.defaultValue(defaultValue: T, async: Boolean = false) =
    apply {
        if (value == null) {
            if (async) postValue(defaultValue)
        } else {
            value = defaultValue
        }
    }

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