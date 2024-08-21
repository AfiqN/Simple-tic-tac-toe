package com.afiqn.gameapp.ui.tictactoe

fun onCellClick(
    currentBoard: List<List<String>>,
    row: Int,
    column: Int,
    player: String,
): Pair<List<List<String>>, Boolean> {
    val updatedBoard = currentBoard.mapIndexed { r, columns ->
        columns.mapIndexed { c, value ->
            if (r == row && c == column) player else value
        }
    }
    val endGame =
        winningCondition(updatedBoard, player) || (updatedBoard.all { r -> r.all { it.isNotEmpty() } })

    return Pair(updatedBoard, endGame)
}

fun winningCondition(
    board: List<List<String>>,
    player: String
): Boolean {
    // Cek baris
    fun checkRows(board: List<List<String>>, player: String): Boolean {
        return board.any { row -> row.all { it == player } }
    }
    // Cek kolom
    fun checkColumns(board: List<List<String>>, player: String): Boolean {
        return (0..2).any { column ->
            board.all { row -> row[column] == player }
        }
    }
    // Cek diagonal
    fun checkDiagonals(board: List<List<String>>, player: String): Boolean {
        return (0..2).all { index -> board[index][index] == player } ||
                (0..2).all { index -> board[index][2 - index] == player }
    }

    return checkRows(board, player) || checkColumns(board, player) || checkDiagonals(board, player)
}
