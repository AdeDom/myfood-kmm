package com.adedom.myfood

import android.os.Bundle
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
import com.adedom.myfood.base.BaseActivity
import com.adedom.myfood.ui.theme.MyfoodTheme
import org.kodein.di.instance

class MainActivity : BaseActivity() {

    private val flow: MainFlow by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyfoodTheme {
                val viewState = flow.viewState.collectAsState()

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column {
                        Greeting("Android")

                        Text(text = "MainActivity : ${viewState.value.text}")

                        Text(text = "Current time : ${viewState.value.currentTime}")

                        TextField(
                            value = viewState.value.text,
                            onValueChange = flow::setTextChangeAction
                        )
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        flow.onCleared()
        super.onDestroy()
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyfoodTheme {
        Greeting("Android")
    }
}