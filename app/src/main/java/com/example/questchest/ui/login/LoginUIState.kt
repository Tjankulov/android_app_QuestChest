package com.example.questchest.ui.login

data class LoginUIState(
    val password: String = "",
    val loggedIn: Boolean = false,
    val isError: Boolean = false
)