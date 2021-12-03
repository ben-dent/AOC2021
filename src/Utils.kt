import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */

fun readInput(name: String) = File("src", "$name.txt").readLines()

/**
 * Reads lines from the given input txt file and returns as a List<Int>.
 */

fun intInput(name: String) = readInput(name).map { it.toInt() }

/**
 * Converts string to md5 hash.
 */

fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

/**
 * Converts from a binary number, digits given as List<Int> to a decimal number
 */

fun binaryToDecimal(binary: List<Int>): Int {
    var mult = 1
    var current = 0
    val l = binary.size

    for (i in l - 1 downTo 0) {
        current += (binary[i] * mult)
        mult *= 2
    }

    return current
}