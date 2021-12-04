fun main() {
    fun part1(input: List<String>): Int {
        val (gameNumbers, boards) = setup(input)

        for (value in gameNumbers) {
            for (board in boards) {
                if (board.playTurn(value)) {
                    return value * board.unmarked.sum()
                }
            }
        }

        return -1
    }

    fun part2(input: List<String>): Int {
        val (gameNumbers, boards) = setup(input)

        val hasWon = HashSet<Board>()

        for (value in gameNumbers) {
            for (board in boards) {
                if (!hasWon.contains(board)) {
                    if (board.playTurn(value)) {
                        if (hasWon.count() == boards.size - 1) {
                            return value * board.unmarked.sum()
                        } else {
                            hasWon.add(board)
                        }
                    }
                }
            }
        }

        return -1
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")

    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}

fun setup(input: List<String>): Pair<List<Int>, List<Board>> {
    val gameNumbers = input[0].split(",").map { Integer.parseInt(it) }

    val boardInfo = input.subList(2, input.size).map { it.trim() }

    val boards = buildBoards(boardInfo)

    return Pair(gameNumbers, boards)
}

fun buildBoards(input: List<String> ): List<Board> {
    val boards = ArrayList<Board>()

    var i = 0

    while (i < input.size) {
        boards.add(Board(input.subList(i, i + 5)))
        i += 6
    }

    return boards
}

class Board(input: List<String>) {
    val unmarked = HashSet<Int>()
    private val itemSet = HashSet<Int>()

    private val board = ArrayList<List<Int>>()

    init {
        for (row in input) {
            val rowItems = row.split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
            itemSet.addAll(rowItems)
            unmarked.addAll(rowItems)
            board.add(rowItems)
        }
    }

    fun playTurn(value: Int): Boolean {
        if (!itemSet.contains(value) || !unmarked.contains(value)) {
            return false
        }

        unmarked.remove(value)

        return wonGame(getCoordinates(value))
    }

    private fun getCoordinates(value: Int): Pair<Int, Int> {
        for (i in 0..board.size) {
            val row = board[i]
            if (row.contains(value)) {
                return Pair(row.indexOf(value), i)
            }
        }

        return Pair(0, 0)
    }

    private fun wonGame(coordinates: Pair<Int, Int>): Boolean {
        val row = board[coordinates.second]

        if (row.none { unmarked.contains(it) }) {
            return true
        }

        return board.map { it[coordinates.first] }.none { unmarked.contains(it) }
    }
}