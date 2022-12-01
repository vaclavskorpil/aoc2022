fun topThreeCalories(input: String): Int {
    val elfCalories = input.split(regex = "\\n[\\n]+".toRegex())

    return elfCalories.map { elfcal ->
        elfcal.split(regex = "\\n".toRegex()).sumOf { it.toInt() }
    }
        .sortedDescending()
        .take(3)
        .sum()
}
fun main() {
    println()
}