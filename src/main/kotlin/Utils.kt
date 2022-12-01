import java.io.File

fun readLines(input : String) : List<String> {
    return File("src/main/resources/$input.txt").readLines()
}