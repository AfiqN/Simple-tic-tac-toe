package com.afiqn.gameapp.ui.vs_ai

fun advancedAI(board: List<List<String>>, player: String): Pair<Int, List<List<String>>> {
    // Evaluate the board state
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
