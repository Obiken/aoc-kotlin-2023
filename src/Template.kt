fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("DayXX_test")
    check(part1(testInput) == 8)

    val input = readInput("DayXX")
    part1(input).println()
    part2(input).println()
}