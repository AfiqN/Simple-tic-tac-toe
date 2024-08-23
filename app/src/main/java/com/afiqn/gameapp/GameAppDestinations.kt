package com.afiqn.gameapp

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed interface GameOption {
    val route: String
}

data object LandingPage : GameOption {
    override val route = "landingPage"
}

data object OptionPage : GameOption {
    override val route = "optionPage"
}

data object AIOptionPage : GameOption {
    override val route = "aiOptionPage"
}

data object VsPlayer : GameOption {
    override val route = "vsPlayer"
}

data object VsAI : GameOption {
    override val route = "vsAI"
    const val optionArg= "AI_type"
    val routeWithArgs = "$route/{$optionArg}"
    val arguments = listOf(
        navArgument(optionArg) { type = NavType.StringType }
    )
}

data object AILevel {
    const val easy = "easy"
    const val advanced = "advanced"
}

val gameAppScreen = listOf(LandingPage, OptionPage, AIOptionPage, VsPlayer, VsAI)