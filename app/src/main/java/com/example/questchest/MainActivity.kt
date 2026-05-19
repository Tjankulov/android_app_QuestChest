package com.example.questchest


import com.example.questchest.ui.login.Login
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.questchest.ui.theme.QuestChestTheme


// VSTOP V APLIKACIJO
class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // za gumb v nastavitvah, če hočem, da bo tema vedno temna, ne glede na nastavitve (darkTheme = true)
            var darkMode by rememberSaveable { mutableStateOf(false) }
            var loggedIn by rememberSaveable { mutableStateOf(false) }

            QuestChestTheme(darkTheme = darkMode) {
                if (loggedIn) {
                    QuestChestApp(
                        darkMode = darkMode,
                        onDarkModeChange = { darkMode = it }
                    )
                } else {
                    Login(
                        onLoginOk = { loggedIn = true }
                    )
                }
            }
        }
    }
}