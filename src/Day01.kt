fun main() {
    fun part1(input: List<String>): Int {
        val numbers: List<Int> = input.map { Integer.parseInt(it) }

        var current = numbers[0]
        var next: Int
        var i = 1
        var n = 0

        while (i < numbers.size) {
            next = numbers[i]

            if (next > current) {
                ++n
            }

            ++i
            current = next
        }

        return n
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
