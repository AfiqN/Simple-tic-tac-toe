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
import com.afiqn.gameapp.AILevel
import com.afiqn.gameapp.R
import com.afiqn.gameapp.ui.components.ButtonLabel
import com.afiqn.gameapp.ui.components.OptionButton

@Composable
fun AIOptionScreen(
    modifier: Modifier = Modifier,
    onCLickEasyAI: (String) -> Unit = {},
    onClickAdvancedAI: (String) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ButtonLabel(textLabel = "Are you tough enough?")
        AIOptionNav(
            onCLickEasyAI = { onCLickEasyAI(AILevel.easy) },
            onClickAdvancedAI = { onClickAdvancedAI(AILevel.advanced) }
        )
        Spacer(modifier = Modifier.size(48.dp))
    }
}

@Composable
fun AIOptionNav(
    onCLickEasyAI: (String) -> Unit = {},
    onClickAdvancedAI: (String) -> Unit = {}
) {
    OptionButton(
        onClickOption = onCLickEasyAI,
        textTitle = "Easy",
        textDesc = "For beginners",
        icon = painterResource(id = R.drawable.ic_robot)
    )
    Spacer(modifier = Modifier.size(24.dp))
    OptionButton(
        onClickOption = onClickAdvancedAI,
        textTitle = "Impossible",
        textDesc = "Only for the brave",
        icon = painterResource(id = R.drawable.ic_ai)
    )
}

@Preview (showBackground = true)
@Composable
fun AIOptionScreenPreview() {
    AIOptionScreen()
}