package com.afiqn.gameapp.ui.vs_player

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.afiqn.gameapp.ui.tictactoe.TicTacToeScreen

@Composable
fun VsPlayerScreen() {
    var board by rememberSaveable { mutableStateOf(List(3) { List(3) { "" } }) }
    var currentPlayer by rememberSaveable { mutableStateOf("X") }
    var endGame by rememberSaveable { mutableStateOf(false) }

    TicTacToeScreen(
        currentBoard = board,
        player = currentPlayer,
        textGameMode = "Player vs Player",
        onBoardUpdate = { newBoard -> board = newBoard },
        onPlayerChange = { newPlayer -> currentPlayer = newPlayer },
        onEndGameUpdate = { isEndGame -> endGame = isEndGame },
        endGame = endGame
    )
}