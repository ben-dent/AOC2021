fun main() {
    fun part1(input: List<Pair<String, Int>>): Int {
        var depth = 0
        var horizontal = 0

        for (pair in input) {
            val command = pair.first
            val X = pair.second

            when (command) {
                "forward" -> horizontal += X
                "down" -> depth += X
                "up" -> depth -= X
            }
        }

        return (depth * horizontal)
    }

    fun part2(input: List<Pair<String, Int>>): Int {
        return input.size
    }

    fun buildPairs(input: List<String>) = input.map { Pair(it.split(" ") [0], Integer.parseInt(it.split(" ") [1]))}

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(buildPairs(testInput)) == 150)

    val input = buildPairs(readInput("Day02"))
    println(part1(input))
    println(part2(input))
}
