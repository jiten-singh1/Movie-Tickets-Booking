package com.jit.ticketbooking.data.remote

import com.jit.ticketbooking.domain.model.MovieSeatsDetailResponse
import retrofit2.Response
import retrofit2.http.GET

interface NetworkApi {
    @GET("v3/80b0e162-2389-41d6-8a0f-7ce5e263d408")
    suspend fun getSeatListing(
    ): Response<MovieSeatsDetailResponse>
}