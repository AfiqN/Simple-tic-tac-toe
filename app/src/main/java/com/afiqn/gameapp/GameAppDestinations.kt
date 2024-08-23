package com.afiqn.gameapp

sealed interface GameOption {
    val route: String
}

data object LandingPage : GameOption {
    override val route = "landingPage"
}

data object OptionPage : GameOption {
    override val route = "optionPage"
}

data object VsPlayer : GameOption {
    override val route = "vsPlayer"
}

data object VsAI : GameOption {
    override val route = "vsAI"
}

val gameAppScreen = listOf(LandingPage, OptionPage, VsPlayer, VsAI)