package com.afiqn.gameapp.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

@Composable
fun ListCreditUrl(
    modifier: Modifier = Modifier,
    icon: Painter,
    text: String,
    uri: String,
    context: Context
) {
    ListCreditInformation(
        modifier = modifier,
        icon = icon,
        text = text,
        intent = { intentUriText(uri, context) }
    )
}

@Composable
fun ListCreditEmail(
    modifier: Modifier = Modifier,
    icon: Painter,
    text: String,
    email: String,
    context: Context
) {
    ListCreditInformation(
        modifier = modifier,
        icon = icon,
        text = text,
        intent = { intentEmailText(email, context) }
    )
}

@Composable
private fun ListCreditInformation(
    modifier: Modifier = Modifier,
    icon: Painter,
    text: String,
    intent: () -> Unit = {}
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(24.dp)
        ) {
            Icon(
                painter = icon,
                contentDescription = "",
                modifier = Modifier.size(20.dp)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            fontStyle = FontStyle.Italic,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable { intent() }
        )
    }
}

private fun intentUriText(uri: String, context: Context) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
    context.startActivity(intent)
}

private fun intentEmailText(email: String, context: Context) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse(/* uriString = */ "mailto:$email")
    }
    context.startActivity(intent)
}