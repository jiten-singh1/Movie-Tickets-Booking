package com.jit.ticketbooking

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TicketBookingApplication : Application() {
    override fun onCreate() {
        super.onCreate()

    }
}