package com.example.questchest

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.*
import com.example.questchest.data.EscapeRoomData
import com.example.questchest.model.EscapeRoom
import com.example.questchest.ui.screens.*


// NAVIGACIJA
enum class QuestChest(@StringRes val title: Int) {
    Homepage(R.string.homepage),
    AddRoom(R.string.add_escape_room),
    Visited(R.string.my_escape_rooms),
    Settings(R.string.settings)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestChestAppBar(
    currentScreen: QuestChest,
    settingsClick: () -> Unit,
    navigateBack: Boolean,
    backClick: () -> Unit,

) {
    CenterAlignedTopAppBar(
        title = {
            Text(stringResource(currentScreen.title),
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        navigationIcon = {
            if (navigateBack) {
                IconButton(onClick = backClick) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Nazaj",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        },
        actions = {
            if (currentScreen != QuestChest.Settings) {
                IconButton(onClick = settingsClick) {
                    Icon(Icons.Filled.Settings, contentDescription = "Nastavitve",
                        tint = MaterialTheme.colorScheme.onPrimary)
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors
            (containerColor = MaterialTheme.colorScheme.primary)
    )
}

@Composable
fun QuestChestApp(
    darkMode: Boolean,
    onDarkModeChange: (Boolean) -> Unit
) {
    val rooms = remember {
        mutableStateListOf<EscapeRoom>().apply {
            addAll(EscapeRoomData().loadEscapeRooms())
        }}
    val navController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: QuestChest.Homepage.name
    val currentScreen = QuestChest.valueOf(currentRoute)

    Scaffold(
        topBar = {
            QuestChestAppBar(
                currentScreen = currentScreen,
                navigateBack = currentScreen != QuestChest.Homepage, // puščica back povsod razen na Homepage
                backClick = { navController.navigateUp() },
                settingsClick = {
                    navController.navigate(QuestChest.Settings.name)
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = QuestChest.Homepage.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(QuestChest.Homepage.name) {
                Homepage(
                    onAddClick = { navController.navigate(QuestChest.AddRoom.name) },
                    onVisitedClick = { navController.navigate(QuestChest.Visited.name) }
                )
            }

            composable(QuestChest.AddRoom.name) {
                AddRoom(
                    saveRoom = { room ->
                        rooms.add(room)
                        navController.navigate(QuestChest.Homepage.name)
                    }
                )
            }

            composable(QuestChest.Visited.name) {
                Visited(
                    rooms = rooms
                )
            }

            composable(QuestChest.Settings.name) {
                Settings(
                    darkMode = darkMode,
                    onDarkModeChange = onDarkModeChange
                )
            }
        }
    }
}
