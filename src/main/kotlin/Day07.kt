sealed interface Node {

    val path: String
    val size: Long
    val parentNode: Node?

    class Dir(override val path: String, override val parentNode: Dir?) : Node {
        init {
            parentNode?.addChild(this)
        }

        val children: MutableList<Node> = mutableListOf()

        override val size: Long by lazy {
            children.sumOf { it.size }
        }

        fun dirs(): List<Dir> {
            val dirs = children.mapNotNull { it as? Dir }
            return dirs + dirs.flatMap { it.dirs() }
        }

        fun addChild(node: Node) {
            children.add(node)
        }

        fun print(i: Int) {
            val ident = (0 until (i * 3)).joinToString("") { " " }

            println("$ident - $path (Dir, size = $size)")
            children.forEach {
                when (it) {
                    is Dir -> it.print(i + 1)
                    is File -> println(" $ident - ${it.path} (File size = ${it.size})")
                }
            }

        }
    }

    class File(override val path: String, override val size: Long, override val parentNode: Node?) : Node
}

sealed interface Command {
    data class CD(val path: String) : Command
    object CDUP : Command
    data class Dir(val path: String) : Command
    data class File(val size: Long, val name: String) : Command
    object LS : Command

    companion object {

        fun of(raw: String): Command {
            val split = raw.split(" ")
            return when {
                split[0].startsWith("$") -> when {
                    split[1] == "ls" -> LS
                    split[2] == ".." -> CDUP
                    else -> CD(split[2])
                }

                split[0].startsWith("dir") -> Dir(split[1])

                else -> File(split[0].toLong(), split[1])
            }

        }
    }

}

fun main() {
    fun createTree(input: Sequence<String>): Node.Dir {
        val root = Node.Dir("/", null)
        input.map(Command::of)
            .fold(root) { currentNode, command ->
                when (command) {
                    is Command.CD -> {
                        currentNode.children.first { it.path == command.path } as Node.Dir
                    }

                    is Command.Dir -> {
                        Node.Dir(command.path, currentNode)
                        currentNode
                    }

                    is Command.File -> {
                        currentNode.addChild(Node.File(command.name, command.size, currentNode))
                        currentNode
                    }

                    is Command.LS -> currentNode
                    is Command.CDUP -> currentNode.parentNode!!
                }
            }
        return root
    }

    val tree = createTree(readAsSequence("Day07_input"))

    tree.print(0)

    fun part1() = tree.dirs().filter { it.size <= 100000 }.sumOf { it.size }
    fun part2() = tree.dirs().sortedBy { it.size }.first { (70000000 - (tree.size - it.size)) > 30000000 }.size


    println(part1())
    println(part2())
}