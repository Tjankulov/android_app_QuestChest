package com.example.questchest.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.questchest.data.EscapeRoomData
import com.example.questchest.model.EscapeRoom
import com.example.questchest.ui.theme.QuestChestTheme


@Composable
fun Visited(
    rooms: List<EscapeRoom>
) {
    var selectedCountry by remember { mutableStateOf("Vse") }

    val countries: List<String> =
        listOf("Vse") + rooms.map { it.country }.toSet().toList()

    val filteredRooms = rooms.filter {
        selectedCountry == "Vse" || it.country == selectedCountry
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        // da bojo še filtri lepo razporejeni, če jih bo več
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(countries) { country ->
                Button(
                    onClick = { selectedCountry = country }
                ) {
                    Text(country)
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        LazyColumn {
            items(filteredRooms) { room ->
                EscapeRoomCard(
                    room = room,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}

@Composable
fun EscapeRoomCard(
    room: EscapeRoom,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("${room.name} - ${room.provider}")
            Text(room.city)
            Text (room.country)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VisitedPreview() {
    QuestChestTheme {
        Visited(
            rooms = EscapeRoomData().loadEscapeRooms()
        )
    }
}
