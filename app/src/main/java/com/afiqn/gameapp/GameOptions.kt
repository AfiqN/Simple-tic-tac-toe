package com.afiqn.gameapp

sealed interface GameOption {
    val route: String
}

data object LandingPage : GameOption {
    override val route = "landingPage"
}

data object VsPlayer : GameOption {
    override val route = "vsPlayer"
}

data object VsAI : GameOption {
    override val route = "vsAI"
}

val GameOptionScreen = listOf(LandingPage, VsPlayer, VsAI)