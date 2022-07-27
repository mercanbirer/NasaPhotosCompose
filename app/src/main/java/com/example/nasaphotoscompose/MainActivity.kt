package com.example.nasaphotoscompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nasaphotoscompose.ui.theme.NasaPhotosComposeTheme
import com.example.nasaphotoscompose.view.NasaPhotosScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NasaPhotosComposeTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "nasa_screen"){
                    composable("nasa_screen") {
                        NasaPhotosScreen(navController = navController)
                    }
                }
            }
        }
    }
}