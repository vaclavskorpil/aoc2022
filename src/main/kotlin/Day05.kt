fun main() {

    fun createCratesMap(crates: String): Map<Int, MutableList<Char>> {
        val map: Map<Int, MutableList<Char>> = List(10) { i -> i to mutableListOf<Char>() }.toMap()
        crates.lines().dropLast(1).forEach { cratesLine ->
            cratesLine
                .chunked(4)
                .map { it.trim() }
                .forEachIndexed { index, crate ->
                    if (crate.isNotEmpty()) {
                        map[index]!!.add(crate[1])
                    }
                }
        }
        return map
    }

    fun part1(input: String) =
        input.split("\n\n").let { (crates, moves) ->
            val map = createCratesMap(crates)
            moves.lines().forEachIndexed { index, move ->
                val (amount, from, to) = "([0-9]+)".toRegex().findAll(move).map {
                    it.value.toInt()
                }.toList()
                println("Step $index ================== Move $amount from ${from - 1} to ${to - 1}")
                map.also {
                    it.forEach { (key, value) -> println("$key: $value") }
                }
                repeat(amount) {
                    map[from - 1]!!.removeFirstOrNull()?.also {
                        map[to - 1]?.add(0, it)
                    }
                }
            }
            map.also {
                it.forEach { (key, value) -> println("$key: $value") }
            }
            return@let map.map {
                it.value.firstOrNull()
            }.filterNotNull().joinToString("")
        }

    fun part2(input: String) =
        input.split("\n\n").let { (crates, moves) ->
            val map = createCratesMap(crates)
            moves.lines().forEachIndexed { index, move ->
                val (amount, from, to) = "([0-9]+)".toRegex().findAll(move).map {
                    it.value.toInt()
                }.toList()
                println("Step $index ================== Move $amount from ${from - 1} to ${to - 1}")
                map.also {
                    it.forEach { (key, value) -> println("$key: $value") }
                }
                List(amount) {
                    map[from - 1]!!.removeFirstOrNull()!!
                }.reversed().forEach {
                    map[to - 1]?.add(0, it)
                }
            }
            map.also {
                it.forEach { (key, value) -> println("$key: $value") }
            }
            return@let map.map {
                it.value.firstOrNull()
            }.filterNotNull().joinToString("")
        }

    val input = readFile("Day05_input")
//    println(part1(input))
    println(part2(input))
}