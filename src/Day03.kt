fun main() {
    fun part1(input: List<String>): Int {
        val l = input[0].length
        val half = (input.size / 2)
        val gamma = ArrayList<Int>()
        val epsilon = ArrayList<Int>()

        for (i in 0 until l) {
            val digit = if (input.map { it[i] }.sumOf { Integer.parseInt(it.toString()) } < half) 0 else 1
            gamma.add(digit)
            epsilon.add(if (digit == 0) 1 else 0)
        }

        return binaryToDecimal(gamma) * binaryToDecimal(epsilon)
    }

    fun part2(input: List<String>): Int {
        var oxNumbers = input
        var coNumbers = input

        val l = input[0].length
        val oxygen = ArrayList<Int>()
        val co2 = ArrayList<Int>()
        var i = 0

        var oxDone = false
        var coDone = false

        while (i < l && !(oxDone && coDone)) {
            if (!oxDone) {
                val oxOnes = oxNumbers.filter { it[i] == '1' }.count()
                val oxZeroes = oxNumbers.size - oxOnes

                oxNumbers = oxNumbers.filter { Integer.parseInt(it[i].toString()) == if (oxOnes >= oxZeroes) 1 else 0 }

                if (oxNumbers.size == 1) {
                    oxygen.addAll(oxNumbers[0].toCharArray().map { Integer.parseInt(it.toString()) })
                    oxDone = true
                }
            }

            if (!coDone) {
                val coOnes = coNumbers.filter { it[i] == '1' }.count()
                val coZeroes = coNumbers.size - coOnes

                coNumbers = coNumbers.filter { Integer.parseInt(it[i].toString()) == if (coOnes < coZeroes) 1 else 0 }

                if (coNumbers.size == 1) {
                    co2.addAll(coNumbers[0].toCharArray().map { Integer.parseInt(it.toString()) })
                    coDone = true
                }
            }

            ++i
        }

        return binaryToDecimal(oxygen) * binaryToDecimal(co2)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}