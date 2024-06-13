package com.jit.ticketbooking.domain.model

import com.google.gson.annotations.SerializedName

data class MovieSeatsDetailResponse(
    @SerializedName("rows") var rows: List<Rows?> = mutableListOf()
)