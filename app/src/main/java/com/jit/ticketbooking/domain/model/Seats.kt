package com.jit.ticketbooking.domain.model

import com.google.gson.annotations.SerializedName


data class Seats(

    @SerializedName("seat_number") var seatNumber: String? = null,
    @SerializedName("status") var status: String? = null

)