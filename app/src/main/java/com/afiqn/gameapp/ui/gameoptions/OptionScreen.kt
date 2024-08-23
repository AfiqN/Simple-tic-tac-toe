package com.afiqn.gameapp.ui.gameoptions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.afiqn.gameapp.R
import com.afiqn.gameapp.ui.components.ButtonLabel
import com.afiqn.gameapp.ui.components.OptionButton
import com.afiqn.gameapp.ui.theme.AppTheme

@Composable
fun OptionScreen(
    modifier: Modifier = Modifier,
    onCLickVsPlayer: () -> Unit = {},
    onClickVsAI: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ButtonLabel(textLabel = "Choose your opponent!")
        GameOptionNav(
            onCLickVsPlayer = onCLickVsPlayer,
            onClickVsAI = onClickVsAI
        )
        Spacer(modifier = Modifier.size(48.dp))
    }
}

@Composable
fun GameOptionNav(
    onCLickVsPlayer: () -> Unit = {},
    onClickVsAI: () -> Unit = {}
) {
    OptionButton(
        onClickOption = onCLickVsPlayer,
        textTitle = "Vs Player",
        textDesc = "Challenge a friend or opponent to a duel.",
        icon = painterResource(id = R.drawable.ic_player)
    )
    Spacer(modifier = Modifier.size(24.dp))
    OptionButton(
        onClickOption = onClickVsAI,
        textTitle = "Vs AI",
        textDesc = "Test your skills against AI opponent.",
        icon = painterResource(id = R.drawable.ic_computer)
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppTheme {
        OptionScreen()
    }
}