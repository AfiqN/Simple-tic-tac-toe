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