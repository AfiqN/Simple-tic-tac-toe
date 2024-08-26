package com.afiqn.gameapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.afiqn.gameapp.R

@Composable
fun SwitchSide(
    selectedSide: String,
    expanded: () -> Boolean,
    onSelectedSideChange: (String) -> Unit,
    onExpandedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.choose_your_side)
        )
        Box {
            OutlinedButton(
                onClick = { onExpandedChange(true) }
            ) {
                Text(text = stringResource(R.string.play_as, selectedSide))
            }

            // Dropdown Menu
            DropdownMenu(
                expanded = expanded(),
                onDismissRequest = { onExpandedChange(false) }
            ) {
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.play_as_x)) },
                    onClick = {
                        onSelectedSideChange("X")
                        onExpandedChange(false)
                    },
                )
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.play_as_o)) },
                    onClick = {
                        onSelectedSideChange("O")
                        onExpandedChange(false)
                    })
            }
        }
    }
}