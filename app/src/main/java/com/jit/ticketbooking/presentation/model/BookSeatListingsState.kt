package com.jit.ticketbooking.presentation.model

import com.jit.ticketbooking.domain.model.MovieSeatsDetailResponse

data class BookSeatListingsState(
    val seatDetails: MovieSeatsDetailResponse? = null,
    val ticketCount: Int = 0,
    val isLoading: Boolean = false,
    val error: String? = null
)
