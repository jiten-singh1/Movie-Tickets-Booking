package com.jit.ticketbooking.domain.repository

import com.jit.ticketbooking.domain.model.MovieSeatsDetailResponse
import com.jit.ticketbooking.util.BaseResult
import kotlinx.coroutines.flow.Flow

interface GetSeatDetailsRepository {
    suspend fun fetchSeatDetails(): Flow<BaseResult<MovieSeatsDetailResponse>>
}