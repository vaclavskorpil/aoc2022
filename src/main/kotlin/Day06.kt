fun main() {

    fun resolve(input: String, msgSize: Int) = input
        .windowed(msgSize, 1).fold(msgSize) { acc, s ->
            if (s.toList().distinct().size != msgSize) {
                acc + 1
            } else return acc
        }

    fun part1(input: String) = resolve(input, 4)

    fun part2(input: String) = resolve(input, 14)

    val input = readFile("Day06_input")

    println(part1(input))
    println(part2(input))
}