package com.afiqn.gameapp.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.afiqn.gameapp.R

@Composable
fun OptionButton(
    modifier: Modifier = Modifier,
    onClickOption: (String) -> Unit = {},
    textTitle: String,
    textDesc: String,
    icon: Painter? = null
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .clickable { onClickOption("") }
            .border(
                1.dp,
                contentColorFor(backgroundColor = MaterialTheme.colorScheme.surface),
                Shapes().medium
            )
            .clip(Shapes().medium),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            if (icon != null) {
                Icon(
                    modifier = Modifier
                        .weight(0.6f)
                        .size(50.dp),
                    painter = icon,
                    contentDescription = null
                )
            }
            Column(
                modifier = Modifier
                    .weight(0.4f)
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = textTitle,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = textDesc,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun ButtonLabel(
    modifier: Modifier = Modifier,
    textLabel: String
) {
    Text(
        modifier = modifier
            .height(48.dp)
            .fillMaxWidth(),
        text = textLabel,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OptionButton(
            modifier = Modifier,
            textTitle = "Button",
            textDesc = "Ini adalah deskripsi bla bla bla",
            icon = painterResource(id = R.drawable.ic_player)
        )
    }
}