package com.jit.ticketbooking.di

import com.jit.ticketbooking.data.repository.SeatDetailsRepositoryImpl
import com.jit.ticketbooking.domain.repository.GetSeatDetailsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindSeatDetailsRepository(
        seatDetailsRepositoryImpl: SeatDetailsRepositoryImpl
    ): GetSeatDetailsRepository

}