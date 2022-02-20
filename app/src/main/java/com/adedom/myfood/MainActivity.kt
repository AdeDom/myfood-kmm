package com.adedom.myfood

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.adedom.core.presentation.main.flow.MainFlow
import com.adedom.myfood.ui.theme.MyfoodTheme
import org.kodein.di.compose.rememberInstance

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyfoodTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyfoodTheme {
        App()
    }
}