package com.afiqn.gameapp.ui.landingpage

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.afiqn.gameapp.R
import com.afiqn.gameapp.ui.theme.AppTheme

@Composable
fun LandingScreen(
    modifier: Modifier = Modifier,
    onCLickStart: () -> Unit = {},
    onClickCredit: () -> Unit = {}
) {
    val horizontalPosition = remember { Animatable(-20f) }
    LaunchedEffect(key1 = true) {
        horizontalPosition.animateTo(
            targetValue = 20f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 3000, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            )
        )
    }

    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_tictactoe),
                contentDescription = null,
                modifier = Modifier
                    .size(180.dp)
                    .weight(0.7f)
                    .graphicsLayer(translationX = horizontalPosition.value)
            )
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .weight(0.3f)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Text(
                    text = stringResource(id = R.string.landing_decs),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .weight(1f)
                        .fillMaxWidth()
                )
                Text(
                    text = "Credit",
                    modifier = Modifier
                        .clickable { onClickCredit() }
                        .padding(bottom = 8.dp),
                    textAlign = TextAlign.End,
                    fontStyle = FontStyle.Italic,
                    textDecoration = TextDecoration.Underline,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            LandingScreenButton(
                modifier = Modifier,
                onClick = onCLickStart,
                text = stringResource(R.string.let_s_begin)
            )
        }
    }
}

@Composable
fun LandingScreenButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String
) {
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(24.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview (showBackground = true)
@Composable
fun LandingScreenPreview() {
    AppTheme {
        LandingScreen()
    }
}