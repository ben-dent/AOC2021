import kotlin.math.max

fun main() {
    fun part1(input: List<Pair<Coordinates, Coordinates>>): Int {
        val (grid, _) = buildGridHorVert(input)

        return grid.filter { it.any { x -> x > 1 } }.map { it.filter { x -> x > 1 } }.map { it.size }.sum()
    }

    fun part2(input: List<Pair<Coordinates, Coordinates>>): Int {
        val (firstGrid, rulesLeft) = buildGridHorVert(input)

        val grid = buildGridDiagonal(rulesLeft, firstGrid)

        return grid.filter { it.any { x -> x > 1 } }.map { it.filter { x -> x > 1 } }.map { it.size }.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = prepareInput(readInput("Day05_test"))
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = prepareInput(readInput("Day05"))
    println(part1(input))
    println(part2(input))
}

private fun buildGridDiagonal(rules: Set<Pair<Coordinates, Coordinates>>, grid: ArrayList<ArrayList<Int>>): List<List<Int>> {
    val newGrid = grid

    for (vent in rules) {
        val start = if (vent.first.y < vent.second.y) vent.first else vent.second
        val end = if (vent.first.y < vent.second.y) vent.second else vent.first

        val xInc = if (start.x < end.x) 1 else -1

        var currentX = start.x
        var currentY = start.y

        while (currentY <= end.y) {
            val currentValue = newGrid[currentY][currentX]
            newGrid[currentY][currentX] = currentValue + 1

            ++currentY
            currentX += xInc
        }
    }

    return newGrid
}

private fun buildGridHorVert(input: List<Pair<Coordinates, Coordinates>>): Pair<ArrayList<ArrayList<Int>>, Set<Pair<Coordinates, Coordinates>>> {
    val size = getSize(input)
    val grid: ArrayList<ArrayList<Int>> = initialiseGrid(size.first, size.second)

    val rules = HashSet(input)

    for (vent in input) {
        val start = vent.first
        val end = vent.second

        if (start.x == end.x) {
            val startY = if (start.y < end.y) start.y else end.y
            val endY = if (start.y < end.y) end.y else start.y

            for (y in startY..endY) {
                val current = grid[y][start.x]
                grid[y][start.x] = current + 1
            }

            rules.remove(vent)

        } else if (start.y == end.y) {
            val startX = if (start.x < end.x) start.x else end.x
            val endX = if (start.x < end.x) end.x else start.x

            val currentRow = grid[start.y]
            for (x in startX..endX) {
                val current = currentRow[x]
                grid[start.y][x] = current + 1
            }

            rules.remove(vent)
        }
    }

    return Pair(grid, rules)
}

private fun initialiseGrid(maxX: Int, maxY: Int): ArrayList<ArrayList<Int>> {
    val grid = ArrayList<ArrayList<Int>>()

    for (y in 0..maxY) {
        val row = ArrayList<Int>()

        for (x in 0..maxX) {
            row.add(0)
        }

        grid.add(row)
    }

    return grid
}

private fun getSize(input: List<Pair<Coordinates, Coordinates>>): Pair<Int, Int> {
    val maxX = input.map { (a, b) -> max(a.x, b.x) }.maxOf { it }
    val maxY = input.map { (a, b) -> max(a.y, b.y) }.maxOf { it }

    return Pair(maxX, maxY)
}

private fun prepareInput(input: List<String>): List<Pair<Coordinates, Coordinates>> {
    return input.map { it.split(" -> ") }.map { x -> x.map { y -> y.split(",").map { z -> z.toInt() }}.map { a -> Coordinates(a[0], a[1]) } }.map { Pair(it[0], it[1]) }
}

class Coordinates(val x: Int, val y: Int)
