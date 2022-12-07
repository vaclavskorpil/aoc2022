import java.io.File

fun readAsSequence(input: String): Sequence<String> {
    return File("src/main/resources/$input.txt").bufferedReader().lineSequence()
}

fun readLines(input: String): List<String> {
    return File("src/main/resources/$input.txt").readLines()
}

fun readFile(input: String): String {
    return File("src/main/resources/$input.txt").readText()
}

