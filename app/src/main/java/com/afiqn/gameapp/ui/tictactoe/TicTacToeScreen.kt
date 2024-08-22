package com.afiqn.gameapp.ui.tictactoe

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.afiqn.gameapp.ui.theme.AppTheme

@Composable
fun TicTacToeScreen() {
    var board by rememberSaveable{ mutableStateOf(List(3) { List(3) { "" } }) }
    var currentPlayer by rememberSaveable { mutableStateOf("X") }
    var endGame by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center // Aligns everything in the center vertically
    ) {
        // Game Title
        Text(
            modifier = Modifier
                .padding(bottom = 20.dp),
            text = "Tic-tac-toe",
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
        )

        // Game Field
        GameField(
            board = board,
            onCellClick = { row, column ->
                val clicked = onCellClick(board, row, column, currentPlayer)
                board = clicked.first
                endGame = clicked.second
                if (!endGame) {
                    currentPlayer = if (currentPlayer == "X") "O" else "X"
                }
            },
            endGame = endGame
        )

        // Add a spacer to create space between the GameField and EndGamePhase
        Spacer(modifier = Modifier.height(16.dp))

        // End Game Phase
        EndGamePhase(board, endGame, currentPlayer)

        // Add another spacer to create space between EndGamePhase and the RestartButton
        Spacer(modifier = Modifier.height(24.dp))

        // Restart Button
        RestartButton(onClick = {
            board = List(3) { List(3) { "" } }
            endGame = false
            currentPlayer = "X"
        }, endGame = endGame)
    }
}



@Composable
fun EndGamePhase(board: List<List<String>>, endGame: Boolean, currentPlayer: String) {
    if (endGame) {
        val winningCondition = winningCondition(board, currentPlayer)
        val message = if (winningCondition) "Player $currentPlayer won!" else "Draw"

        Text(
            modifier = Modifier
                .padding(top = 20.dp),
            fontSize = 30.sp,
            text = message
        )
    } else {
        Text(
            modifier = Modifier
                .padding(top = 20.dp),
            text = "Current player: $currentPlayer",
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun RestartButton(
    onClick: () -> Unit,
    endGame: Boolean
) {
    if (endGame) {
        Button(
            onClick = onClick
        ) {
            Text(text = "Restart")
        }
    }
}

@Composable
fun GameField(
    modifier: Modifier = Modifier,
    board: List<List<String>>,
    onCellClick: (Int, Int) -> Unit = { _, _ -> },
    endGame: Boolean
) {
    Column(
        modifier = modifier,
    ) {
        for (row in 0..2) {
            Row {
                for (column in 0..2) {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .border(2.dp, Color.Gray)
                            .clickable {
                                if (board[row][column].isEmpty() && !endGame) {
                                    onCellClick(row, column)
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = board[row][column], fontSize = 24.sp)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            TicTacToeScreen()
        }
    }
}