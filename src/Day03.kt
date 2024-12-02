fun main() {
    fun part1(input: List<String>): Int {
        val row: Int = findRowLength(input)
        val column: Int = findColumnLength(input)
        val array = Array(row) { CharArray(column) }
        input.forEachIndexed { index, line ->
            array[index] = line.toCharArray()
        }
        return sumAllEngineParts(array)
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 4361)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}

fun sumAllEngineParts(array: Array<CharArray>): Int {
    val allEngineParts = mutableListOf<Int>()
    findAllPartNumbers(array).forEach {
        if (isNearSymbol(it, array)) allEngineParts.add(it.value)
    }

    return allEngineParts.sum()
}

fun findAllPartNumbers(array: Array<CharArray>): List<PartNumber> {
    val partNumbers: MutableList<PartNumber> = mutableListOf()
    array.forEachIndexed { index, row ->
        val numbers = Regex("""\d+""").findAll(row.contentToString()) // '\d+' captures single digits, and not whole numbers as expected
            .map(MatchResult::value)
            .toList()

        row.contentToString().println()
        numbers.println()

        numbers.forEach {
            val start = row.contentToString().indexOf(it)
            val end = start + it.length
            val coordinates: MutableList<Pair<Int, Int>> = mutableListOf()
            for (i in start..end) {
                coordinates.add(Pair(start + i, index))
            }
            partNumbers.add(PartNumber(it.toInt(), coordinates))
        }
    }

    return partNumbers
}

fun isNearSymbol(partNumber: PartNumber, array: Array<CharArray>): Boolean {
    val symbols = "|!#¤%&/=?\\\$£*-:;+"
    var adjacent = ""

    partNumber.positions.forEach { pair ->
        adjacentPositions(pair).forEach {
            if (it.first > 0 && it.second > 0 && it.first < array.size && it.second < array[0].size)
                adjacent = adjacent.plus(array[it.first][it.second])
        }
    }

    return Regex(symbols).containsMatchIn(adjacent)
}

fun adjacentPositions(position: Pair<Int, Int>): List<Pair<Int, Int>> {
    val listOfAdjacent: MutableList<Pair<Int, Int>> = mutableListOf()
    listOfAdjacent.add(Pair(position.first - 1, position.second - 1))
    listOfAdjacent.add(Pair(position.first, position.second - 1))
    listOfAdjacent.add(Pair(position.first + 1, position.second - 1))
    listOfAdjacent.add(Pair(position.first, position.second - 1))
    listOfAdjacent.add(Pair(position.first, position.second + 1))
    listOfAdjacent.add(Pair(position.first + 1, position.second - 1))
    listOfAdjacent.add(Pair(position.first + 1, position.second))
    listOfAdjacent.add(Pair(position.first + 1, position.second + 1))

    return listOfAdjacent
}

fun findRowLength(input: List<String>): Int {
    return input.first().length
}

fun findColumnLength(input: List<String>): Int {
    return input.size
}

data class PartNumber(val value: Int, val positions: List<Pair<Int, Int>>)

