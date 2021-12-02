fun main() {
    fun part1(input: List<Int>): Int {
        var current = input[0]
        var next: Int
        var i = 1
        var n = 0

        while (i < input.size) {
            next = input[i]

            if (next > current) {
                ++n
            }

            ++i
            current = next
        }

        return n
    }

    fun part2(input: List<Int>): Int {
        var start = 0
        var prev = 0
        var n = 0

        while (start < input.size - 3) {
            val current = input[start] + input[start + 1] + input[start + 2]

            if (current > prev) {
                ++n
            }

            prev = current
            ++start
        }

        return n
    }

    // test if implementation meets criteria from the description, like:
    val testInput = intInput("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = intInput("Day01")
    println(part1(input))
    println(part2(input))
}
