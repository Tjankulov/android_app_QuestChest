package com.example.questchest.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.questchest.model.EscapeRoom
import com.example.questchest.ui.theme.QuestChestTheme


@Composable
fun AddRoom(
    saveRoom: (EscapeRoom) -> Unit
) {
    var name by remember {mutableStateOf("") }
    var provider by remember {mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("")}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(name, { name = it }, label = { Text("Ime sobe") })
        OutlinedTextField(provider, { provider = it }, label = { Text("Ponudnik") })
        OutlinedTextField(city, { city = it }, label = { Text("Mesto") })
        OutlinedTextField(country, { country = it }, label = { Text("Država") })

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                saveRoom(
                    EscapeRoom(
                        name = name,
                        provider = provider,
                        city = city,
                        country = country
                    )
                )
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Shrani")
        }
    }
}




@Preview
@Composable
fun AddRoomPreview() {
    QuestChestTheme {
        AddRoom(
            saveRoom = {}
        )
    }
}
