package com.afiqn.gameapp.ui.vs_ai

import kotlin.random.Random

fun easyAI(
    board: List<List<String>>,
    player: String
): List<List<String>> {
    val emptyCells = mutableListOf<Pair<Int, Int>>()

    board.mapIndexed { rowIndex, rows ->
        rows.mapIndexed { colIndex, cell ->
            if (cell.isEmpty()) {
                emptyCells.add(Pair(rowIndex, colIndex))
            }
        }
    }

    if (emptyCells.isNotEmpty()) {
        val randomIndex = Random.nextInt(emptyCells.size)
        val (row, col) = emptyCells[randomIndex]
        return updateBoard(board, row, col, player)
    }

    return board
}

fun advancedAI(board: List<List<String>>, player: String): Pair<Int, List<List<String>>> {
    // Evaluate the board state
    val isEmptyBoard = board.all { row -> row.all { it.isEmpty() } }

    if (isEmptyBoard) {
        // If the board is empty, make a random move
        val randomRow = Random.nextInt(3)
        val randomCol = Random.nextInt(3)
        val newBoard = updateBoard(board, randomRow, randomCol, player)
        return Pair(0, newBoard) // Return 0 as score since it's the first move
    }

    val score = evaluateBoard(board, player)

    // If the game is over, return the score and the current board
    if (score != 0 || isBoardFull(board)) {
        return Pair(score, board)
    }

    // Find all empty cells
    val emptyCells = mutableListOf<Pair<Int, Int>>()
    board.forEachIndexed { rowIndex, row ->
        row.forEachIndexed { colIndex, cell ->
            if (cell.isEmpty()) {
                emptyCells.add(Pair(rowIndex, colIndex))
            }
        }
    }

    // Initialize best move and best score
    var bestMove: Pair<Int, Int>? = null
    var bestScore = Int.MIN_VALUE

    // Iterate over all empty cells and evaluate the resulting board states
    for ((row, col) in emptyCells) {
        val newBoard = updateBoard(board, row, col, player)
        val (currentScore, _) = advancedAI(newBoard, if (player == "X") "O" else "X")

        val adjustedScore = -currentScore

        if (adjustedScore > bestScore) {
            bestScore = adjustedScore
            bestMove = Pair(row, col)
        }
    }

    // Return the best score and the best move applied to the board
    return Pair(bestScore, updateBoard(board, bestMove!!.first, bestMove.second, player))
}