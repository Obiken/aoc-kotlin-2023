fun main() {
    fun part1(input: List<String>): Int {
        val pointList: MutableList<Int> = mutableListOf()
        input.forEach { pointList.add(getPointsOnCard(it)) }
        return pointList.sum()
    }

    fun part2(input: List<String>): Int {
        val cardList: MutableList<Int> = mutableListOf()
        for (i in 1..input.size) cardList.add(1)
        return getTotalNumberOfCards(input, cardList)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 13)

    val input = readInput("Day04")
    part1(input).println()

    // test if implementation meets criteria from the description, like:
    val testInput2 = readInput("Day04_test2")
    check(part2(testInput2) == 30)

    part2(input).println()
}

// Part 2
fun getTotalNumberOfCards(input: List<String>, cardList: MutableList<Int>): Int {
    val winners: MutableList<Int> = mutableListOf()
    input.forEach { card ->
        val winningNumbers = getWinningNumbers(card)
        val yourNumbers = getYourNumbers(card)
        var numberOfWinningNumbers = 0
        winningNumbers.forEach { if (yourNumbers.contains(it)) numberOfWinningNumbers += 1 }
        winners.add(numberOfWinningNumbers)
    }
    winners.forEachIndexed { index, wins ->
        for (i in 0..wins) {
            if (i > 0 && (index + i) < winners.size) {
                cardList[ index + i ] += cardList[ index ]
            }
        }
    }
    return cardList.sum()
}


// Part 1
fun getPointsOnCard(card: String): Int {
    val winningNumbers = getWinningNumbers(card)
    val yourNumbers = getYourNumbers(card)
    var numberOfWinningNumbers = 0
    winningNumbers.forEach { if (yourNumbers.contains(it)) numberOfWinningNumbers += 1 }

    var points = 0
    if (numberOfWinningNumbers != 0) {
        points = 1
        for (i in 1..< numberOfWinningNumbers) {
            points *= 2
        }
    }

    return points
}

fun getWinningNumbers(card: String): List<Int> {
    val winningStringList = card.substringAfter(":").substringBefore("|").trim().split(" ")
    return winningStringList.filter { it.isNotBlank() }.map { it.toInt() }
}

fun getYourNumbers(card: String): List<Int> {
    val winningStringList = card.substringAfter("|").trim().split(" ")
    return winningStringList.filter { it.isNotBlank() }.map { it.toInt() }
}