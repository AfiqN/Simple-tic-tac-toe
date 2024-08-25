package com.afiqn.gameapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.afiqn.gameapp.ui.gameoptions.AIOptionScreen
import com.afiqn.gameapp.ui.gameoptions.OptionScreen
import com.afiqn.gameapp.ui.landingpage.CreditScreen
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
        startDestination = LandingPage.route,
        modifier = modifier
    ) {
        composable(route = LandingPage.route) {
            LandingScreen(
                onCLickStart = { navController.navigateSingleTopTo(OptionPage.route) },
                onClickCredit = { navController.navigateSingleTopTo(CreditPage.route) }
            )
        }
        composable(route = CreditPage.route) {
            CreditScreen()
        }
        composable(route = OptionPage.route) {
            OptionScreen(
                onCLickVsPlayer = { navController.navigateSingleTopTo(VsPlayer.route) },
                onClickVsAI = { navController.navigateSingleTopTo(AIOptionPage.route) }
            )
        }
        composable(route = AIOptionPage.route) {
            AIOptionScreen(
                onCLickEasyAI = { level ->
                    navController.navigateToVsAI(level)
                },
                onClickAdvancedAI = { level ->
                    navController.navigateToVsAI(level)
                }
            )
        }
        composable(route = VsPlayer.route) {
            VsPlayerScreen()
        }
        composable(
            route = VsAI.routeWithArgs,
            arguments = VsAI.arguments
        ) { navBackStackEntry ->
            val level =
                navBackStackEntry.arguments?.getString(VsAI.optionArg)
            VsAIScreen(level)
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(route) {
            saveState = true
        }

        launchSingleTop = true
        restoreState = true
    }

fun NavHostController.navigateToVsAI(level: String) {
    this.navigateSingleTopTo("${VsAI.route}/$level")
}