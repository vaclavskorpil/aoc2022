enum class Play(val points: Int) {
    ROCK(1), PAPER(2), SCISSORS(3);

    fun resolve(other: Play) =
        when (this) {
            ROCK -> when (other) {
                ROCK -> Result.DRAW
                PAPER -> Result.LOSS
                SCISSORS -> Result.WIN
            }

            PAPER -> when (other) {
                ROCK -> Result.WIN
                PAPER -> Result.DRAW
                SCISSORS -> Result.LOSS
            }

            SCISSORS -> when (other) {
                ROCK -> Result.LOSS
                PAPER -> Result.WIN
                SCISSORS -> Result.DRAW
            }
        }

    fun desiredOutcome(result: Result) =
        when (result) {
            Result.WIN -> when (this) {
                ROCK -> PAPER
                PAPER -> SCISSORS
                SCISSORS -> ROCK
            }

            Result.LOSS -> {
                when (this) {
                    ROCK -> SCISSORS
                    PAPER -> ROCK
                    SCISSORS -> PAPER
                }
            }

            Result.DRAW -> when (this) {
                ROCK -> ROCK
                PAPER -> PAPER
                SCISSORS -> SCISSORS
            }
    }
}

enum class Result(val points: Int) {
    WIN(6), DRAW(3), LOSS(0);
}

fun String.elfPlay() =
    when (this) {
        "A" -> Play.ROCK
        "B" -> Play.PAPER
        "C" -> Play.SCISSORS
        else -> throw IllegalArgumentException("Invalid play")
    }

fun String.yourPlay() =
    when (this) {
        "X" -> Play.ROCK
        "Y" -> Play.PAPER
        "Z" -> Play.SCISSORS
        else -> throw IllegalArgumentException("Invalid play")
    }

fun String.parseOutcome() =
    when (this) {
        "X" -> Result.LOSS
        "Y" -> Result.DRAW
        "Z" -> Result.WIN
        else -> throw IllegalArgumentException("Invalid outcome")
    }

fun getPoints(elfPlay: Play, yourPlay: Play) =
    yourPlay.resolve(elfPlay).points.plus(yourPlay.points)

fun getPoints(elfPlay : Play, outcome : Result) =
    elfPlay.desiredOutcome(outcome).points.plus(outcome.points)

fun main() {

    fun part1(input: List<String>) =
        input
            .sumOf {
                val round = it.split(" ")
                getPoints(round.first().elfPlay(), round.last().yourPlay())
            }

    fun part2(input: List<String>)=
        input
            .sumOf {
                val round = it.split(" ")
                getPoints(round.first().elfPlay(), round.last().parseOutcome())
            }


    val input = readLines("Day02_input")
    println(part1(input))
    println(part2(input))
}