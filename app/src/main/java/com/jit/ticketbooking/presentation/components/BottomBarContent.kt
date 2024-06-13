package com.jit.ticketbooking.presentation.components

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jit.ticketbooking.R
import com.jit.ticketbooking.presentation.model.BookSeatListingsState
import com.jit.ticketbooking.ui.theme.BookTicketAppTheme
import com.jit.ticketbooking.ui.theme.Dimensions
import com.jit.ticketbooking.ui.theme.grayLight
import com.jit.ticketbooking.ui.theme.greenHighlight

@Composable
fun BottomBarContent(state: BookSeatListingsState) {
    Column(
        modifier = Modifier.padding(
            top = Dimensions.padding_10,
            bottom = Dimensions.padding_10
        ),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = Dimensions.padding_10),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .height(Dimensions.boxSize_20)
                        .width(Dimensions.boxSize_20)
                        .background(color = grayLight)
                )
                Text(
                    modifier = Modifier.padding(start = Dimensions.padding_10),
                    text = stringResource(id = R.string.sold),
                    fontSize = Dimensions.fontSize_16
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .height(Dimensions.boxSize_20)
                        .width(Dimensions.boxSize_20)
                        .border(BorderStroke(Dimensions.borderWidth, greenHighlight))
                )
                Text(
                    modifier = Modifier.padding(start = Dimensions.padding_10),
                    text = stringResource(id = R.string.available),
                    fontSize = Dimensions.fontSize_16
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .height(Dimensions.boxSize_20)
                        .width(Dimensions.boxSize_20)
                        .background(color = greenHighlight)
                )
                Text(
                    modifier = Modifier.padding(start = Dimensions.padding_10),
                    text = stringResource(id = R.string.selected),
                    fontSize = Dimensions.fontSize_16
                )
            }
        }
        Column {
            val context = LocalContext.current
            if (state.ticketCount > 1) {
                Button(
                    modifier = Modifier.padding(top = Dimensions.padding_10),
                    onClick = {
                        Toast.makeText(
                            context,
                            "Thank you!!.",
                            Toast.LENGTH_SHORT
                        ).show()

                    },
                    shape = RoundedCornerShape(50.dp),

                    ) {
                    Text(text = stringResource(id = R.string.pay), color = Color.White)
                }
            }

        }
    }
}


@Composable
@Preview(showBackground = true, heightDp = 200)
fun BottomBarContentPreview() {
    val state = BookSeatListingsState(
        null,
        3,
        false
    )
    BookTicketAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            BottomBarContent(state)
        }
    }
}