package com.jit.ticketbooking.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.jit.ticketbooking.domain.model.MovieSeatsDetailResponse
import com.jit.ticketbooking.domain.model.Rows
import com.jit.ticketbooking.domain.model.Seats
import com.jit.ticketbooking.presentation.model.BookSeatListingsState
import com.jit.ticketbooking.ui.theme.BookTicketAppTheme
import com.jit.ticketbooking.ui.theme.Dimensions
import com.jit.ticketbooking.ui.theme.grayLight
import com.jit.ticketbooking.ui.theme.greenHighlight


@Composable
fun SeatSelection(
    state: BookSeatListingsState,
    onSeatSelect: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(Dimensions.padding_10)
    ) {

        val checkedSeatsState = remember { mutableStateMapOf<String, Boolean>() }

        LazyVerticalGrid(
            GridCells.Fixed(9),
            horizontalArrangement = Arrangement.spacedBy(Dimensions.padding_5),
            verticalArrangement = Arrangement.spacedBy(Dimensions.padding_5),
            contentPadding = PaddingValues(Dimensions.padding_5),
        ) {
            state.seatDetails?.rows?.forEach { rowTitle ->
                item {
                    Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        Box(
                            modifier = Modifier
                                .size(Dimensions.boxSize),
                            contentAlignment = Alignment.Center
                        ) {
                            rowTitle?.rowNumber?.let {
                                Text(
                                    text = it,
                                )
                            }
                        }
                    }
                }
                if (rowTitle?.rowNumber.equals("-")) {
                    emptyRowItem()
                } else {
                    rowTitle?.seats?.let {
                        items(it.size) { item ->
                            val seatNumber = rowTitle.seats[item].seatNumber
                            val rowIdentifier = rowTitle.rowNumber
                            val isEnable = !rowTitle.seats[item].status.equals("booked")
                            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                                if (rowTitle.seats[item].status.equals("empty")) {
                                    Box(
                                        modifier = Modifier
                                            .size(Dimensions.boxSize)
                                    )
                                } else {
                                    SeatState(
                                        isEnabled = isEnable,
                                        isSelected = checkedSeatsState.contains(rowIdentifier + seatNumber),
                                        seatNumber = seatNumber.toString()
                                    ) { selected, seat ->
                                        if (selected) {
                                            checkedSeatsState.remove(rowIdentifier + seatNumber)
                                            onSeatSelect(checkedSeatsState.size)
                                        } else {
                                            checkedSeatsState.put(rowIdentifier + seatNumber, true)
                                            onSeatSelect(checkedSeatsState.size)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun LazyGridScope.emptyRowItem() {
    item(span = { GridItemSpan(maxLineSpan) }) {}
}

@Composable
fun SeatState(
    isEnabled: Boolean = false,
    isSelected: Boolean = false,
    seatNumber: String = "",
    onClick: (Boolean, String) -> Unit = { _, _ -> },
) {
    var borderColor by remember { mutableStateOf(greenHighlight) }


    val seatColor = when {
        !isEnabled -> grayLight
        isSelected -> greenHighlight
        else -> Color.White
    }

    val textColor = when {
        isSelected -> Color.White
        else -> Color.Black
    }

    Box(modifier = Modifier
        .size(Dimensions.boxSize)
        .border(
            width = Dimensions.borderWidth,
            color = if (!isEnabled) grayLight else borderColor,
            shape = RoundedCornerShape(Dimensions.seatCornerRadius)
        )
        .clip(RoundedCornerShape(Dimensions.seatCornerRadius))
        .background(color = seatColor)
        .clickable {
            if (isEnabled) {
                onClick(isSelected, seatNumber)
            } else {
                borderColor = if (isSelected) Color.White else greenHighlight

            }

        }
        .padding(Dimensions.padding_5), contentAlignment = Alignment.Center) {
        Text(
            fontSize = Dimensions.fontSize_10,
            text = seatNumber,
            color = textColor
        )
    }
}


@Composable
@Preview(showBackground = true, heightDp = 80)
fun SeatSelectionPreview() {
    var seats: ArrayList<Seats> = arrayListOf(
        Seats("1", "Booked"),
        Seats("2", "available"), Seats("3", "available")
    )

    var rows: List<Rows?> = arrayListOf(Rows("A", seats))
    val seatDetails = MovieSeatsDetailResponse(rows)


    val state = BookSeatListingsState(
        seatDetails,
        3,
        false
    )
    BookTicketAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            SeatSelection(
                state,
                onSeatSelect = {}
            )
        }
    }
}