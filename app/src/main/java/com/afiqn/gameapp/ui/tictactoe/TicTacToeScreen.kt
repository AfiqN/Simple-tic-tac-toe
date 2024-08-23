package com.afiqn.gameapp.ui.tictactoe

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
fun TicTacToeScreen(
    modifier: Modifier = Modifier,
    currentBoard: List<List<String>>,
    player: String,
    endGame: Boolean,
    onBoardUpdate: (List<List<String>>) -> Unit,
    onPlayerChange: (String) -> Unit,
    onEndGameUpdate: (Boolean) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        GameField(
            modifier = Modifier.weight(1f),
            board = currentBoard,
            onCellClick = { row, column ->
                val clicked = onCellClick(currentBoard, row, column, player)
                onBoardUpdate(clicked.first)
                onEndGameUpdate(clicked.second)
                if (!clicked.second) {
                    onPlayerChange(if (player == "X") "O" else "X")
                }
            },
            endGame = endGame,
            player = player
        )
        EndGamePhase(
            modifier = Modifier.weight(0.5f),
            currentBoard, endGame, player,
            onClick = {
                onBoardUpdate(List(3) { List(3) { "" } })
                onEndGameUpdate(false)
                onPlayerChange("X")
            }
        )
    }
}



@Composable
fun EndGamePhase(
    modifier: Modifier = Modifier,
    board: List<List<String>>,
    endGame: Boolean,
    currentPlayer: String,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        if (endGame) {
            val winningCondition = winningCondition(board, currentPlayer)
            val message = if (winningCondition) "Player $currentPlayer won!" else "Draw!"

            Text(
                fontSize = 30.sp,
                text = message
            )
            RestartButton(onClick = onClick)
        } else {
            Text(
                text = "Current player: $currentPlayer",
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun RestartButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
    ) {
        Text(text = "Restart")
    }
}

@Composable
fun GameField(
    modifier: Modifier = Modifier,
    board: List<List<String>>,
    onCellClick: (Int, Int) -> Unit = { _, _ -> },
    endGame: Boolean,
    player: String
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Bottom
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
                            }
                            .background(
                                winningBoard(board, column, row, player)
                            ),
                        contentAlignment = Alignment.Center,

                    ) {
                        Text(text = board[row][column], fontSize = 24.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun winningBoard(
    board: List<List<String>>,
    col: Int,
    row: Int,
    player: String
): Color {
    val winningCells = checkWinningCell(board, player)
    val celRow = Pair(row, col)
    return if (celRow in winningCells) {
        MaterialTheme.colorScheme.surfaceVariant
    } else {
        MaterialTheme.colorScheme.surface
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            var board by rememberSaveable { mutableStateOf(List(3) { List(3) { "" } }) }
            var currentPlayer by rememberSaveable { mutableStateOf("X") }
            var endGame by rememberSaveable { mutableStateOf(false) }

            TicTacToeScreen(
                currentBoard = board,
                player = currentPlayer,
                onBoardUpdate = { newBoard -> board = newBoard },
                onPlayerChange = { newPlayer -> currentPlayer = newPlayer },
                onEndGameUpdate = { isEndGame -> endGame = isEndGame },
                endGame = endGame
            )
        }
    }
}