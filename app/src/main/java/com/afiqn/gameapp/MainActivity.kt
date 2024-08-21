package com.afiqn.gameapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.afiqn.gameapp.ui.theme.AppTheme
import com.afiqn.gameapp.ui.tictactoe.TicTacToeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                GameApp()
            }
        }
    }
}

@Composable
fun GameApp() {
    TicTacToeScreen()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        GameApp()
    }
}