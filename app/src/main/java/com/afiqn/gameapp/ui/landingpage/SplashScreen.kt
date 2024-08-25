package com.afiqn.gameapp.ui.landingpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.afiqn.gameapp.R
import kotlinx.coroutines.delay

private const val SplashWaitTime: Long = 200

@Composable
fun SplashScreen(onTimeout: () -> Unit, modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        val currentOnTimeout by rememberUpdatedState(onTimeout)

        LaunchedEffect(Unit) {
            delay(SplashWaitTime)
            currentOnTimeout()
        }
        Image(
            modifier = Modifier.size(148.dp),
            painter = painterResource(id = R.drawable.ic_tictactoe),
            contentDescription = null
        )
    }
}

@Preview (showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(onTimeout = {})
}

