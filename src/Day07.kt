import kotlin.math.abs

fun main() {
    fun part1(input: List<Int>): Int {
        return input.map { a -> input.map { b -> abs(a - b) }.sum() }.minOf { it }
    }

    fun part2(input: List<Int>): Int {
        return (1..input.maxOf { it }).map { a -> input.map { b -> calcCost(abs(a - b)) }.sum() }.minOf { it }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = splitIntsOnComma(readFullInput("Day07_test"))
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)

    val input = splitIntsOnComma(readFullInput("Day07"))
    println(part1(input))
    println(part2(input))
}

fun calcCost(moves: Int): Int {
    return (1..moves).sum()
}
