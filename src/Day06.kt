fun main() {
    fun part1(input: List<Int>, day: Int = 1, finalDay: Int = 80): Long {
        val nextInput = ArrayList<Int>()

        for (fish in input) {
            if (fish > 0) {
                nextInput.add(fish - 1)
            } else {
                nextInput.add(6)
                nextInput.add(8)
            }
        }

        return if (day == finalDay) nextInput.size.toLong() else part1(nextInput, day + 1, finalDay)
    }

    fun part2(input: List<Int>, finalDay: Int = 256): Long {
        var n = input.size.toLong()
        val newSpawn = HashMap<Int, Int>()

        input.forEach { newSpawn[it] = 1 }

        for (day in 1..finalDay) {
            if (newSpawn.containsKey(day)) {
                n += newSpawn[day]!!

                val six = day + 6
                val eight = day + 8

                if (newSpawn.containsKey(six)) {
                    val current = newSpawn[six]!!
                    newSpawn[six] = current + 1
                } else {
                    newSpawn[six] = 1
                }

                if (newSpawn.containsKey(eight)) {
                    val current = newSpawn[eight]!!
                    newSpawn[eight] = current + 1
                } else {
                    newSpawn[eight] = 1
                }
            }
//            val next = ArrayList<Int>()
//
//            for (fish in current) {
//                if (fish > 0) {
//                    next.add(fish - 1)
//                } else {
//                    next.add(6)
//                    next.add(8)
//                    ++n
//                }
//            }
//
//            current = next
        }

        val ret = newSpawn.keys.map { newSpawn[it]!! }.sumOf { it }

        return n
    }

    // test if implementation meets criteria from the description, like:
    val testInput = prepareInput(readFullInput("Day06_test"))
    check(part1(testInput) == 5934L)

    val check = part2(testInput)

    check(part2(testInput) == 26984457539L)

    val input = prepareInput(readFullInput("Day06"))
    println(part1(input))
    println(part2(input))
}

fun prepareInput(input: String): List<Int> {
    return input.split(",").map { it.toInt() }
}
