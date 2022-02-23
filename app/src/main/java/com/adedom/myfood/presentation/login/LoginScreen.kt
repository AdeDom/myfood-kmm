package com.adedom.myfood.presentation.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adedom.core.presentation.login.flow.LoginFlow
import org.kodein.di.compose.rememberInstance

@Composable
fun LoginScreen() {
    val flow: LoginFlow by rememberInstance()

    val state by flow.viewState.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            TextField(
                value = state.username,
                onValueChange = flow::setUsernameChangeAction,
                placeholder = {
                    Text(text = "Username")
                }
            )
            TextField(
                value = state.password,
                onValueChange = flow::setPasswordChangeAction,
                placeholder = {
                    Text(text = "Password")
                }
            )
            Button(
                onClick = flow::setLoginClickAction,
                enabled = !state.isLoading
            ) {
                Text(text = "Login")
            }
        }

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(100.dp)
            )
        }
    }
}