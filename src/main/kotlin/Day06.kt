fun main() {

    fun part1(input: String) =
        input.windowed(14, 1).fold(14) { acc, s ->
            if (s.toList().distinct().size != 14) {
                acc + 1
            } else return acc
        }

//    fun part2(input: String) =

    val input = readFile("Day06_input")

    println(part1(input))
//    println(part2(input))
}