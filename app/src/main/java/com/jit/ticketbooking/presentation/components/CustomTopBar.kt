package com.jit.ticketbooking.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.jit.ticketbooking.R
import com.jit.ticketbooking.presentation.model.BookSeatListingsState
import com.jit.ticketbooking.ui.theme.BookTicketAppTheme
import com.jit.ticketbooking.ui.theme.Dimensions


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String, subtitle: String, state: BookSeatListingsState) {
    TopAppBar(
        modifier = Modifier.height(Dimensions.size_80),
        title = {
            Column {
                Row(modifier = Modifier.padding(top = Dimensions.padding_8)) {
                    //Movie Title
                    Text(
                        modifier = Modifier.padding(end = Dimensions.padding_5),
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = Dimensions.fontSize_16,
                        color = Color.White
                    )
                    // UA rating
                    Box(
                        modifier = Modifier
                            .border(
                                width = Dimensions.borderWidth,
                                color = Color.White,
                                shape = CircleShape
                            )
                            .clip(CircleShape)
                    ) {
                        Text(
                            modifier = Modifier.padding(Dimensions.padding_3),
                            text = stringResource(R.string.ua),
                            fontSize = Dimensions.fontSize_12,
                            color = Color.White
                        )
                    }

                }
                //Subtitle and button
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = Dimensions.padding_5),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(subtitle, fontSize = Dimensions.fontSize_10, color = Color.White)

                    TextButton(
                        onClick = { },
                        modifier = Modifier
                            .padding(top = Dimensions.padding_3, start = Dimensions.padding_5)
                            .border(
                                border = BorderStroke(
                                    width = Dimensions.borderWidth,
                                    color = Color.White,
                                ),
                                shape = RoundedCornerShape(Dimensions.cornerRadius),
                            )
                            .height(Dimensions.size_30)
                    ) {
                        Text(
                            text = "${state.ticketCount} Tickets",
                            fontSize = Dimensions.fontSize_10,
                            color = Color.White
                        )
                        Icon(
                            Icons.Filled.Edit,
                            stringResource(R.string.close_icon),
                            modifier = Modifier.padding(start = Dimensions.padding_10),
                            tint = Color.White
                        )
                    }
                }
            }

        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Red),
        actions = {
            Column {
                IconButton(onClick = { }) {
                    Icon(
                        Icons.Filled.Close,
                        stringResource(R.string.close_icon),
                        tint = Color.White
                    )
                }
            }
        }
    )
}


@Composable
@Preview(showBackground = true, heightDp = 80)
fun TopBarPreview() {
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
            TopBar(
                title = stringResource(R.string.movie),
                subtitle = stringResource(R.string.movie_details),
                state
            )
        }
    }
}
