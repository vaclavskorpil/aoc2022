val abcd = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

fun priority(c: Char): Int = abcd.indexOf(c) + 1
fun main() {

    fun part1(input: List<String>) =
        input.map { line ->
            val half = (line.length) / 2
            val first = line.substring(half)
            val second = line.substring(0, half)

            first.filter { second.contains(it) }.toCharArray().distinct().toList().also { println(it) }
        }.sumOf { chars ->
            chars.sumOf { priority(it) }
        }

    fun part2(input: List<String>) =
        input.chunked(3) { (first, second, third) ->

            val badge =
                first.filter { second.contains(it) && third.contains(it) }.toCharArray().distinct().toList().first()
            badge.also { println(it) }
        }.sumOf { char ->
            priority(char)
        }

    val input = readLines("Day03_input")
//    println(part1(input))
    println(part2(input))
}