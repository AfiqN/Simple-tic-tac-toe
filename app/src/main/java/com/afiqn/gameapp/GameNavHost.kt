package com.afiqn.gameapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.afiqn.gameapp.ui.landingpage.LandingScreen
import com.afiqn.gameapp.ui.vs_ai.VsAIScreen
import com.afiqn.gameapp.ui.vs_player.VsPlayerScreen

@Composable
fun GameNavHost(
    navController: NavHostController,
    modifier : Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = VsPlayer.route,
        modifier = modifier
    ) {
        composable(route = LandingPage.route) {
            LandingScreen()
        }
        composable(route = VsPlayer.route) {
            VsPlayerScreen()
        }
        composable(route = VsAI.route) {
            VsAIScreen()
        }
    }
}