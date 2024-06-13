package com.jit.ticketbooking.presentation.event

sealed class BookSeatEvent {
    object None : BookSeatEvent()
    data class TicketCounter(val count: Int) : BookSeatEvent()
}