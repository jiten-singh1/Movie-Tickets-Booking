package com.jit.ticketbooking.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jit.ticketbooking.domain.usecase.GetSeatDetailsUseCase
import com.jit.ticketbooking.presentation.event.BookSeatEvent
import com.jit.ticketbooking.presentation.model.BookSeatListingsState
import com.jit.ticketbooking.util.BaseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookSeatViewModel @Inject constructor(
    private val getSeatDetailsUseCase: GetSeatDetailsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(BookSeatListingsState())
    val state = _state.asStateFlow()

    private val _stateSharedFlow = MutableSharedFlow<BookSeatListingsState>()
    val stateShare = _stateSharedFlow.asSharedFlow()



    private val _loginState = MutableLiveData<Boolean>()
    val loginState: LiveData<Boolean>
        get() = _loginState


    init {
        getSeatListings()

    }

    private fun getSeatListings() {
        viewModelScope.launch(Dispatchers.IO) {
            getSeatDetailsUseCase()
                .onStart {
                    setLoading()
                }
                .catch {
                    hideLoading()
                }
                .collect { result ->
                    hideLoading()
                    when (result) {
                        is BaseResult.Success -> {
                            result.data?.let { listings ->
                                _state.value = state.value.copy(
                                    seatDetails = listings
                                )
                            }
                        }

                        is BaseResult.Error -> _state.value =
                            state.value.copy(error = "Error: " + result.message)

                        is BaseResult.Loading -> {
                            _state.value = state.value.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }
    }

    private fun setLoading() {
        _state.value = BookSeatListingsState(isLoading = true)

    }

    private fun hideLoading() {
        _state.value = BookSeatListingsState(isLoading = false)
    }

    fun onEvent(event: BookSeatEvent) {
        when (event) {
            is BookSeatEvent.TicketCounter -> {
                _state.value = state.value.copy(
                    ticketCount = event.count
                )
            }

            else -> {}
        }
    }

}