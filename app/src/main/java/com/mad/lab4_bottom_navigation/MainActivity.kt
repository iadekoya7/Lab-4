package com.mad.lab4_bottom_navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.mad.lab4_bottom_navigation.ui.theme.Lab4BottomNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab4BottomNavigationTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                HomeScreen(navController = navController, composeHostController = navController)

            }
        }
    }
}

