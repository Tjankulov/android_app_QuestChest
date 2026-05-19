package com.example.questchest.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.questchest.model.EscapeRoom
import com.example.questchest.ui.theme.QuestChestTheme
import com.example.questchest.network.fetchEscapeRooms
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@Composable
fun Homepage(
    onAddClick: () -> Unit,
    onVisitedClick: () -> Unit
) {
    var apiRooms by remember { mutableStateOf<List<EscapeRoom>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        apiRooms = withContext(Dispatchers.IO) {
            fetchEscapeRooms()
        }
        isLoading = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = onAddClick) {
                Icon(Icons.Default.Add, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Dodaj obisk")
            }

            Button(onClick = onVisitedClick) {
                Icon(Icons.Default.Favorite, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Moje obiskane")
            }
        }

        Spacer(Modifier.height(24.dp))

        when {
            isLoading -> {
                CircularProgressIndicator()
            }

            apiRooms.isEmpty() -> {
                Text(
                    text = "Trenutno ne morem prikazati podatkov.",
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            else -> {
                LazyColumn {
                    items(apiRooms) { room ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(16.dp),
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Place,
                                    contentDescription = null,
                                    modifier = Modifier.size(40.dp),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                                Column {
                                    Text(room.name, style = MaterialTheme.typography.titleMedium)
                                    Text("${room.city}, ${room.country}")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun HomepagePreview() {
    QuestChestTheme {
        Homepage(
            onAddClick = {},
            onVisitedClick = {}
        )
    }
}