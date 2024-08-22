package com.afiqn.gameapp.ui.vs_ai

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.afiqn.gameapp.ui.tictactoe.TicTacToeScreen
import com.afiqn.gameapp.ui.tictactoe.winningCondition

@Composable
fun VsAIScreen() {
    var board by rememberSaveable { mutableStateOf(List(3) { List(3) { "" } }) }
    var currentPlayer by rememberSaveable { mutableStateOf("X") }
    var endGame by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(board, currentPlayer) {
        if (currentPlayer == "O" && !endGame) {
            board = easyAIBoard(board, currentPlayer)
            endGame =
                winningCondition(board, currentPlayer) || (board.all { r -> r.all { it.isNotEmpty() } })
            currentPlayer = if (endGame) "O" else "X"
        }
    }

    TicTacToeScreen(
        currentBoard = board,
        player = currentPlayer,
        onBoardUpdate = { newBoard -> board = newBoard },
        onPlayerChange = { newPlayer -> currentPlayer = newPlayer },
        onEndGameUpdate = { isEndGame -> endGame = isEndGame },
        endGame = endGame
    )
}

fun easyAIBoard(
    board: List<List<String>>,
    player: String
): List<List<String>> {
    return easyAI(board, player)
}