package com.jit.ticketbooking.domain.usecase

import com.jit.ticketbooking.domain.model.MovieSeatsDetailResponse
import com.jit.ticketbooking.domain.repository.GetSeatDetailsRepository
import com.jit.ticketbooking.util.BaseResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSeatDetailsUseCase @Inject constructor(private val getSeatDetailsRepository: GetSeatDetailsRepository) {
    suspend operator fun invoke(): Flow<BaseResult<MovieSeatsDetailResponse>> {
        return getSeatDetailsRepository.fetchSeatDetails()
    }
}