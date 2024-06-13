package com.jit.ticketbooking.data.repository

import com.jit.ticketbooking.data.remote.NetworkApi
import com.jit.ticketbooking.domain.model.MovieSeatsDetailResponse
import com.jit.ticketbooking.domain.repository.GetSeatDetailsRepository
import com.jit.ticketbooking.util.BaseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SeatDetailsRepositoryImpl @Inject constructor(
    private val networkApi: NetworkApi,
) : GetSeatDetailsRepository {
    override suspend fun fetchSeatDetails(): Flow<BaseResult<MovieSeatsDetailResponse>> {
        return flow {
            try {
                val response = networkApi.getSeatListing()
                if (response.isSuccessful) {
                    val body = response.body()
                    val movieSeatsDetailResponse = body?.let { MovieSeatsDetailResponse(it.rows) }
                    emit(BaseResult.Success(movieSeatsDetailResponse))
                } else emit(BaseResult.Error(response.code().toString()))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(BaseResult.Error("Couldn't load data"))
            }
        }
    }
}