package com.afiqn.gameapp.ui.tictactoe

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
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
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.text.font.FontWeight
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
    textGameMode: String,
    onBoardUpdate: (List<List<String>>) -> Unit,
    onPlayerChange: (String) -> Unit,
    onEndGameUpdate: (Boolean) -> Unit,
    changeSide: @Composable () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        InformationPanel(
            modifier = Modifier.weight(0.5f),
            textMode = textGameMode,
            changeSide = changeSide
        )
        GameField(
            modifier = Modifier,
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
fun InformationPanel(
    modifier: Modifier = Modifier,
    textMode: String,
    changeSide: @Composable () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        changeSide()
        Row (
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = "Game mode:",
                fontSize = 16.sp
            )
            Text(
                text = " $textMode",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
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
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Restart",
            fontWeight = FontWeight.Bold
        )
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
                    val clicked = board[row][column].isNotEmpty()

                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .border(2.dp, Color.Gray)
                            .clickable {
                                if (!clicked && !endGame) {
                                    onCellClick(row, column)
                                }
                            }
                            .background(
                                winningBoard(board, column, row, player, endGame)
                            ),
                        contentAlignment = Alignment.Center,
                    ) {
                        this@Row.AnimatedVisibility(
                            visible = clicked,
                            enter = fadeIn(animationSpec = tween(500)) + scaleIn(initialScale = 0.8f, animationSpec = tween(500)),
                            exit = fadeOut(animationSpec = tween(500)) + scaleOut(targetScale = 0.8f, animationSpec = tween(500))
                        ) {
                            Text(
                                text = board[row][column],
                                fontSize = 24.sp,
                            )
                        }
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
            color = colorScheme.background
        ) {
            var board by rememberSaveable { mutableStateOf(List(3) { List(3) { "" } }) }
            var currentPlayer by rememberSaveable { mutableStateOf("X") }
            var endGame by rememberSaveable { mutableStateOf(false) }

            TicTacToeScreen(
                currentBoard = board,
                player = currentPlayer,
                textGameMode = "Player vs AI",
                onBoardUpdate = { newBoard -> board = newBoard },
                onPlayerChange = { newPlayer -> currentPlayer = newPlayer },
                onEndGameUpdate = { isEndGame -> endGame = isEndGame },
                endGame = endGame
            )
        }
    }
}