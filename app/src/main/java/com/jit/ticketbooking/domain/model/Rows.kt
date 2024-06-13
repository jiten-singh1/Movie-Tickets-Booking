package com.jit.ticketbooking.domain.model

import com.google.gson.annotations.SerializedName

data class Rows(
    @SerializedName("row_number") var rowNumber: String? = null,
    @SerializedName("seats") var seats: List<Seats> = mutableListOf()

)