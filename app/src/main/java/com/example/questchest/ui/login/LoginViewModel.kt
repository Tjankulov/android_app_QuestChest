package com.example.questchest.ui.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.questchest.data.password

class LoginViewModel : ViewModel() {

    var uiState = mutableStateOf(LoginUIState())
        private set

    fun updatePassword(newPassword: String) {
        uiState.value = uiState.value.copy(
            password = newPassword,
            isError = false
        )
    }

    fun login(): Boolean {
        return if (uiState.value.password == password) {
            uiState.value = uiState.value.copy(
                loggedIn = true,
                isError = false
            )
            true
        } else {
            uiState.value = uiState.value.copy(
                isError = true
            )
            false
        }
    }
}