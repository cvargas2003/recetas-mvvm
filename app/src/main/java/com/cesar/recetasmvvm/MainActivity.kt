package com.cesar.recetasmvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.cesar.recetasmvvm.presentation.screens.HomeScreen
import com.cesar.recetasmvvm.presentation.screens.HomeScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {

            MaterialTheme {

                HomeScreen()
            }
        }
    }
}