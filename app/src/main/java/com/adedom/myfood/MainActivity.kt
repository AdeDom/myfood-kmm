package com.adedom.myfood

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.adedom.myfood.presentation.app.AppCompose
import com.adedom.myfood.ui.theme.MyfoodTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyfoodTheme {
                AppCompose()
            }
        }
    }
}