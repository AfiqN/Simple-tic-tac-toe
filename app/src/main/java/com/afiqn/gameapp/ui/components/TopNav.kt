package com.afiqn.gameapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.afiqn.gameapp.R

@Composable
fun TopNav(
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit = {}
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = null,
            modifier = Modifier
                .clickable { onClickBack() }
        )
        Text(
            text = "Tic-Tac-Toe",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .weight(1f)
        )
        Spacer(modifier = Modifier.size(24.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun TopNavPreview() {
    TopNav()
}