class Grid() {

    val grid = ArrayList<ArrayList<Int>>()

    fun addRow(string: String) {
        val row = ArrayList<Int>()
        for (i in 0..string.length - 1) {
            row.add(string[i].toString().toInt())
        }
        grid.add(row)
    }

    fun checkVisible(row: Int, collumn: Int): Int? {
        val tree = grid[row][collumn]

        val maxColumn = grid.lastIndex
        val maxRow = grid.first().lastIndex

        if (row == 0 || collumn == 0 || row == maxRow || collumn == maxColumn) {
            return tree
        }

        val list = listOf(
            ((collumn - 1).coerceAtLeast(0) downTo 0).firstOrNull {
                println(" to left tree $tree checking $row:$it tree ${grid[row][it]} ")
                grid[row][it] >= tree
            },
            ((collumn + 1).coerceAtMost(maxRow)..maxRow).firstOrNull {
                println(" to righ tree $tree checking $row:$it tree ${grid[row][it]} ")
                grid[row][it] >= tree
            },
            ((row - 1).coerceAtLeast(0) downTo 0).firstOrNull {
                val check = grid[it][collumn]
                println(" to bottom tree $tree checking $it:$collumn $check ")
                check >= tree
            },
            ((row + 1).coerceAtMost(maxColumn)..maxColumn).firstOrNull {
                val check = grid[it][collumn]
                println(" to top tree $tree checking $it:$collumn $check ")
                check >= tree
            },
        )
        println("tree $tree row $row, collumn $collumn, higher $list")

        return if (list.filterNotNull().size != 4) tree else null
    }

    fun checkVisibleDistance(row: Int, collumn: Int): Int? {
        val tree = grid[row][collumn]

        val maxRowSize = grid.lastIndex
        val maxCollumnSize = grid.first().lastIndex

        if (row == 0 || collumn == 0 || row == maxCollumnSize || collumn == maxRowSize) {
            return null
        }

        val list = listOf(
            collumn - (((collumn - 1).coerceAtLeast(0) downTo 0).firstOrNull { grid[row][it] >= tree } ?: 0),

            (((collumn + 1).coerceAtMost(maxCollumnSize)..maxCollumnSize).firstOrNull { grid[row][it] >= tree }
                ?: maxCollumnSize) - collumn,

            row - (((row - 1).coerceAtLeast(0) downTo 0).firstOrNull {
                val check = grid[it][collumn]
                check >= tree
            } ?: 0),

            (((row + 1).coerceAtMost(maxRowSize)..maxRowSize).firstOrNull {
                val check = grid[it][collumn]
                println(" to top tree $tree checking $it:$collumn $check ")
                check >= tree
            } ?: maxRowSize) - row,
        )

        val result = list.reduce { acc, i -> acc * i }
        println("tree $tree row $row, collumn $collumn, higher $list, reslult $result")



        return list.reduce { acc, i -> acc * i }
    }

    fun bestView() =
        sequence {
            for (row in 0..grid.lastIndex) {
                for (collumn in 0..grid.first().lastIndex) {
                    checkVisibleDistance(row, collumn)?.let { yield(it) }
                }
            }
        }.max()

    fun countVisible() =
        sequence {
            for (row in 0..grid.lastIndex) {
                for (collumn in 0..grid.first().lastIndex) {
                    checkVisible(row, collumn)?.let { yield(it) }
                }
            }
        }.count()

}

fun main() {

    val grid = Grid()
    val input = readLines("Day08_input")

    input.forEach {
        grid.addRow(it)
    }

    fun part1() = grid.countVisible()

    fun part2() = grid.bestView()

//    println(part1())
    println(part2())
}