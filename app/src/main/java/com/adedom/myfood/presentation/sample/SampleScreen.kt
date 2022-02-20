package com.adedom.myfood.presentation.sample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.adedom.core.presentation.main.flow.MainFlow
import org.kodein.di.compose.rememberInstance

@Composable
fun SampleScreen() {
    val flow: MainFlow by rememberInstance()

    val viewState = flow.viewState.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            Text(text = "MainActivity : ${viewState.value.text}")

            Text(text = "Current time : ${viewState.value.currentTime}")

            TextField(
                value = viewState.value.text,
                onValueChange = flow::setTextChangeAction
            )
        }
    }
}