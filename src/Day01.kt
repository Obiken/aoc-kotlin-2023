fun main() {
    fun part1(input: List<String>): Int {
        val calibrationValuesList: MutableList<Int> = mutableListOf()
        input.forEach { calibrationValuesList.add(calibrationValues(it)) }
        return calibrationValuesList.sum()
    }

    fun part2(input: List<String>): Int {
        val calibrationValuesList: MutableList<Int> = mutableListOf()
        input.forEach { calibrationValuesList.add(calibrationValuesPartTwo(it)) }
        return calibrationValuesList.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 142)

    val input = readInput("Day01")
    check(part1(input) == 54877)

    // test if implementation meets criteria for part 2:
    val testInputPartTwo = readInput("Day01_test2")
    check(part2(testInputPartTwo) == 281)
    val testInputPartThree = readInput("Day01_test2_1")
    check(part2(testInputPartThree) == 64)
    part2(input).println()
}

fun calibrationValues(line: String): Int {
    val firstDigit = line.first { it.isDigit() }
    val lastDigit = line.last { it.isDigit() }
    return (firstDigit.toString().plus(lastDigit.toString())).toInt()
}

fun calibrationValuesPartTwo(line: String): Int {
    val firstDigit = findFirstDigitInString(line)
    val lastDigit = findLastDigitInString(line)
    return (firstDigit.toString().plus(lastDigit.toString())).toInt()
}

fun findFirstDigitInString(line: String): Int {
    val digitWords = mapOf(
        1 to "one",
        2 to "two",
        3 to "three",
        4 to "four",
        5 to "five",
        6 to "six",
        7 to "seven",
        8 to "eight",
        9 to "nine"
    )

    val firstCharDigitIndex = line.indexOfFirst { it.isDigit() }
    var firstCharDigit = 0
    if (firstCharDigitIndex != -1) firstCharDigit = line.first { it.isDigit() }.digitToInt()

    val digitMap: MutableMap<Int, Int> = mutableMapOf()

    digitWords.forEach {
        if (it.value in line) digitMap[it.key] = line.indexOf(it.value)
    }
    if (firstCharDigitIndex != -1 && digitMap.contains(firstCharDigit) && digitMap[firstCharDigit]!! > firstCharDigitIndex) {
        digitMap[firstCharDigit] = firstCharDigitIndex
    } else if (firstCharDigitIndex != -1 && !digitMap.contains(firstCharDigit)){
        digitMap[firstCharDigit] = firstCharDigitIndex
    }

    return digitMap.minByOrNull { it.value }?.key ?: 0
}

fun findLastDigitInString(line: String): Int {
    val digitWords = mapOf(
        1 to "one",
        2 to "two",
        3 to "three",
        4 to "four",
        5 to "five",
        6 to "six",
        7 to "seven",
        8 to "eight",
        9 to "nine"
    )

    val lastCharDigitIndex = line.indexOfLast { it.isDigit() }
    var lastCharDigit = 0
    if (lastCharDigitIndex != -1) lastCharDigit = line.last { it.isDigit() }.digitToInt()

    val digitMap: MutableMap<Int, Int> = mutableMapOf()

    digitWords.forEach {
        if (it.value in line) digitMap[it.key] = line.lastIndexOf(it.value)
    }
    if (lastCharDigitIndex != -1 && digitMap.contains(lastCharDigit) && digitMap[lastCharDigit]!! < lastCharDigitIndex) {
        digitMap[lastCharDigit] = lastCharDigitIndex
    } else if (lastCharDigitIndex != -1 && !digitMap.contains(lastCharDigit)){
        digitMap[lastCharDigit] = lastCharDigitIndex
    }

    return digitMap.maxByOrNull { it.value }?.key ?: 0
}