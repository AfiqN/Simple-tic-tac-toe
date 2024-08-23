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

fun checkWinningCell(
    board: List<List<String>>, player: String
): List<Pair<Int, Int>> {
    val winningCells = mutableListOf<Pair<Int, Int>>()

    // Cek baris
    for (row in board.indices) {
        if (board[row].all { it == player }) {
            for (col in board[row].indices) {
                winningCells.add(Pair(row, col))
            }
            break
        }
    }

    // Cek kolom
    for (col in board[0].indices) {
        if (board.all { it[col] == player }) {
            for (row in board.indices) {
                winningCells.add(Pair(row, col))
            }
            break
        }
    }

    // Cek diagonal
    if (board.indices.all { index -> board[index][index] == player }) {
        for (index in board.indices) {
            winningCells.add(Pair(index, index))
        }
    }

    if (board.indices.all { index -> board[index][2 - index] == player }) {
        for (index in board.indices) {
            winningCells.add(Pair(index, 2 - index))
        }
    }

    return winningCells
}