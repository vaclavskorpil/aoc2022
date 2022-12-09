data class HT(val h: H, val t: Coord)
data class H(val x: Int, val y: Int, val dir: Char){

    fun posEq(h: H) = h.x == x && h.y == y
    fun oneStepBehind(h: H) = x in h.x - 1..h.x + 1 && y in h.y - 1..h.y + 1
    fun follow(h: H): List<Coord> {
        if (posEq(h)) return listOf(this)
        if (oneStepBehind(h)) return listOf(this)
        return sequence<Coord> {
            when (h.dir) {
                'U' -> {
                    if (h.x != x) yield(Coord(h.x, y + 1))
                    yieldAll((y + 1 until h.y).map { Coord(h.x, it) })

                }

                'D' -> {
                    if (h.x != x) yield(Coord(h.x, y - 1))
                    yieldAll((y - 1 downTo h.y + 1).map { Coord(h.x, it) })
                }

                'L' -> {
                    if (h.y != y) yield(Coord(x - 1, h.y))
                    yieldAll((x - 1 downTo h.x + 1).map { Coord(it, h.y) })
                }

                'R' -> {
                    if (h.y != y) yield(Coord(x + 1, h.y))
                    yieldAll((x + 1 until h.x).map { Coord(it, h.y) })
                }
            }
        }.toList()
    }
}
data class Coord(val x: Int, val y: Int) {

    fun posEq(h: H) = h.x == x && h.y == y
    fun oneStepBehind(h: H) = x in h.x - 1..h.x + 1 && y in h.y - 1..h.y + 1
    fun follow(h: H): List<Coord> {
        if (posEq(h)) return listOf(this)
        if (oneStepBehind(h)) return listOf(this)
        return sequence<Coord> {
            when (h.dir) {
                'U' -> {
                    if (h.x != x) yield(Coord(h.x, y + 1))
                    yieldAll((y + 1 until h.y).map { Coord(h.x, it) })

                }

                'D' -> {
                    if (h.x != x) yield(Coord(h.x, y - 1))
                    yieldAll((y - 1 downTo h.y + 1).map { Coord(h.x, it) })
                }

                'L' -> {
                    if (h.y != y) yield(Coord(x - 1, h.y))
                    yieldAll((x - 1 downTo h.x + 1).map { Coord(it, h.y) })
                }

                'R' -> {
                    if (h.y != y) yield(Coord(x + 1, h.y))
                    yieldAll((x + 1 until h.x).map { Coord(it, h.y) })
                }
            }
        }.toList()
    }
}


fun main() {

    val input = readLines("Day09_input")

    fun part1(input: List<String>): Int {
        val allSteps = mutableListOf<Coord>(Coord(0, 0))
        input.fold(HT(H(0, 0, 's'), Coord(0, 0))) { (h, t), step ->
            val direction = step[0]
            val stepSize = step.split(" ")[1].toInt()
            val h = when (direction) {
                'R' -> H(h.x + stepSize, h.y, direction)
                'L' -> H(h.x - stepSize, h.y, direction)
                'U' -> H(h.x, h.y + stepSize, direction)
                else -> H(h.x, h.y - stepSize, direction)
            }
            val tSteps = t.follow(h)
            println("H goes $h")
            tSteps.forEach {
                println("T goes $it")
            }



            println("========== H at $h")
            allSteps.addAll(tSteps)
            HT(h, tSteps.last())
        }
        return allSteps.distinct().onEach {

        }.count()
    }

    println(part1(input))
//    println(part2())
}
