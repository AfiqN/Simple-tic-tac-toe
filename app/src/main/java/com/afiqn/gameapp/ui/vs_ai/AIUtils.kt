package com.afiqn.gameapp.ui.vs_ai

import com.afiqn.gameapp.ui.tictactoe.winningCondition


fun updateBoard(
    board: List<List<String>>,
    row: Int,
    col: Int,
    player: String
): List<List<String>> {
    return board.mapIndexed { rowIndex, rows ->
        rows.mapIndexed { colIndex, cell ->
            if (rowIndex == row && colIndex == col) {
                player
            } else {
                cell
            }
        }
    }
}

fun evaluateBoard(board: List<List<String>>, player: String): Int {
    // Check for winning conditions
    return if (winningCondition(board, player)) {
        1
    } else if (winningCondition(board, if (player == "X") "O" else "X")) {
        -1
    } else {
        0
    }
}

fun isBoardFull(board: List<List<String>>): Boolean {
    // Check if there are any empty cells left
    return board.all { row -> row.all { cell -> cell.isNotEmpty() } }
}
