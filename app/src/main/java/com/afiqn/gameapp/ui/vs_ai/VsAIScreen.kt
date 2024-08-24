package com.afiqn.gameapp.ui.vs_ai

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.afiqn.gameapp.ui.components.SwitchSide
import com.afiqn.gameapp.ui.tictactoe.TicTacToeScreen
import com.afiqn.gameapp.ui.tictactoe.winningCondition

@Composable
fun VsAIScreen(level: String?) {
    var board by rememberSaveable { mutableStateOf(List(3) { List(3) { "" } }) }
    var currentPlayer by rememberSaveable { mutableStateOf("X") }
    var endGame by rememberSaveable { mutableStateOf(false) }
    var expanded by rememberSaveable { mutableStateOf(false) }
    var selectedSide by rememberSaveable { mutableStateOf("X") }

    LaunchedEffect(board, currentPlayer, endGame) {
        if (!endGame && currentPlayer != selectedSide) {
            // Update board based on AI difficulty level
            board = if (level == "easy") {
                easyAIBoard(board, currentPlayer)
            } else {
                advancedAIBoard(board, currentPlayer)
            }

            // Check if the game has ended
            endGame = winningCondition(board, currentPlayer) || (board.all { row -> row.all { it.isNotEmpty() } })

            // Switch current player only if the game hasn't ended
            if (!endGame) {
                currentPlayer = if (currentPlayer == "X") "O" else "X"
            }
        }
    }

    TicTacToeScreen(
        currentBoard = board,
        player = currentPlayer,
        textGameMode = if (level == "easy") "Player vs Easy AI" else "Player vs Advanced AI",
        onBoardUpdate = { newBoard -> board = newBoard },
        onPlayerChange = { newPlayer -> currentPlayer = newPlayer },
        onEndGameUpdate = { isEndGame -> endGame = isEndGame },
        endGame = endGame,
        changeSide = {
            SwitchSide(
                selectedSide = selectedSide,
                expanded = { expanded },
                onSelectedSideChange = { newSide ->
                    selectedSide = newSide
                    // Reset the game state when the side changes
                    board = List(3) { List(3) { "" } }
                    currentPlayer = newSide
                    endGame = false

                    // Trigger AI move immediately if user plays as "O"
                    if (selectedSide == "O") {
                        currentPlayer = "X" // Set current player to AI
                        board = if (level == "easy") {
                            easyAIBoard(board, currentPlayer)
                        } else {
                            advancedAIBoard(board, currentPlayer)
                        }

                        // Check for endgame condition after AI's move
                        endGame = winningCondition(
                            board,
                            currentPlayer
                        ) || board.all { row -> row.all { it.isNotEmpty() } }

                        // Switch back to human player only if the game hasn't ended
                        if (!endGame) {
                            currentPlayer = "O"
                        }
                    }
                },
                onExpandedChange = { newExpanded -> expanded = newExpanded }
            )
        }
    )
}

fun easyAIBoard(
    board: List<List<String>>,
    player: String
): List<List<String>> {
    return easyAI(board, player)
}

fun advancedAIBoard(
    board: List<List<String>>,
    player: String
): List<List<String>> {
    return advancedAI(board, player).second
}