package com.afiqn.gameapp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AppScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable (() -> Unit)? = null,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            topBar?.let {
                Box(modifier = Modifier.statusBarsPadding()) {
                    it()
                }
            }
        },
        content = content
    )
}