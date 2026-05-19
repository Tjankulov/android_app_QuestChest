package com.example.questchest.ui.login


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.questchest.R
import com.example.questchest.ui.theme.QuestChestTheme

@Composable
fun Login(
    viewModel: LoginViewModel = viewModel(),
    onLoginOk: () -> Unit
) {
    val state by viewModel.uiState

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.questchest_logo),
                contentDescription = null,
                modifier = Modifier.width(200.dp)
            )

            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = state.password,
                onValueChange = viewModel::updatePassword,
                label = { Text("Geslo") },
                isError = state.isError,
                visualTransformation = PasswordVisualTransformation()
            )

            if (state.isError) {
                Text(
                    text = "Napačno geslo",
                    color = MaterialTheme.colorScheme.error
                )
            }

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = {
                    val success = viewModel.login()
                    if (success) {
                        onLoginOk()
                    }
                }
            ) {
                Text("Potrdi")
            }
        }
    }
}

@Preview
@Composable
fun LoginPreview() {
    QuestChestTheme {
        Login(onLoginOk = {})
    }
}

