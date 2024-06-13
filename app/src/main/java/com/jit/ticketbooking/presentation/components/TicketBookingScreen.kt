package com.jit.ticketbooking.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jit.ticketbooking.R
import com.jit.ticketbooking.presentation.event.BookSeatEvent
import com.jit.ticketbooking.presentation.viewmodel.BookSeatViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketBookingScreen(
    viewModel: BookSeatViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(R.string.movie),
                subtitle = stringResource(R.string.movie_details),
                state.value
            )
        }

    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {

                if (state.value.isLoading) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else if (state.value.error != null) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = state.value.error!!,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                } else {
                    SeatSelection(state.value,
                        onSeatSelect = {
                            viewModel.onEvent(BookSeatEvent.TicketCounter(it))
                        })
                }
            }
            BottomBarContent(state.value)
        }
    }
}



