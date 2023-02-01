package com.bertholucci.data.model

import com.google.gson.annotations.SerializedName

class SearchResponse(
    @SerializedName("show") val show: ShowResponse
)