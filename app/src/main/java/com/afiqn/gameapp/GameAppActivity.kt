package com.afiqn.gameapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.afiqn.gameapp.ui.components.AppScaffold
import com.afiqn.gameapp.ui.components.TopNav
import com.afiqn.gameapp.ui.landingpage.SplashScreen
import com.afiqn.gameapp.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var showLandingScreen by remember {
                mutableStateOf(true)
            }

            if (showLandingScreen) {
                SplashScreen(onTimeout = { showLandingScreen = false })
            } else {
                GameApp()
            }
        }
    }
}

@Composable
fun GameApp() {
    AppTheme {
        val navController = rememberNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination
        val currentScreen =
            gameAppScreen.find { currentDestination?.route?.startsWith(it.route) == true } ?: LandingPage

        AppScaffold(
            topBar = {
                if (currentScreen != LandingPage) {
                    TopNav(
                        onClickBack = { navController.navigateUp() }
                    )
                }
            }
        ){ innerPadding ->
            GameNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        GameApp()
    }
}