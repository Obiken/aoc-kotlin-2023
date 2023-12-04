fun main() {
    fun part1(input: List<String>): Int {
        val possibleGames: MutableList<Int> = mutableListOf()
        input.forEach {
            val game = playGame(it)
            if (isPossibleGame(game)) possibleGames.add(game.id)
        }
        return possibleGames.sum()
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 8)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}

data class Game(val id: Int, val red: Int, val green: Int, val blue: Int)

data class Pull(val red: Int, val green: Int, val blue: Int)

fun playGame(line: String): Game {
    val id = line.substringAfter("Game ").substringBefore(":").toInt()
    line.substringAfter(":")
    return Game(id, 0, 0, 0)
}

fun isPossibleGame(game: Game): Boolean {
    TODO("Not yet implemented")
}
