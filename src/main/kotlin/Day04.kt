fun main() {

    fun part1(input: List<String>) =
        input.filter {
            val (first, seccond) = it.split(",")
            val (a, b) = first.split("-").map { it.toInt() }
            val (x, y) = seccond.split("-").map { it.toInt() }

            a <= x && b >= y || a >= x && b <= y
        }.count()

    fun overlap(a: Int, b: Int, x: Int, y: Int) =
        y in a..b || b in x..y

    fun part2(input: List<String>) =
        input.filter {
            val (first, seccond) = it.split(",")
            val (a, b) = first.split("-").map { it.toInt() }
            val (x, y) = seccond.split("-").map { it.toInt() }

            overlap(a, b, x, y)
        }.count()

    val input = readLines("Day04_input")
//    println(part1(input))
    println(part2(input))
}