package cinema

private const val PRICE_FOR_LESS_THAN_SIXTY = 10

private const val PRICE_FOR_MORE_THAN_SIXTY = 8

/**
 * This clas is implemented for Cinema Manager JetBrains academy project:
 *  https://hyperskill.org/projects/138
 *  @author Georgi Naumov
 *  gonaumov@gmail.com for contacts and suggestions
 */
class CinemaManager(private val rows: Int, private val seats: Int) {

    private var seatState: MutableList<MutableList<String>> = initSeats(rows, seats)
    private var totalSeats = calculateTotalSeats(rows, seats)
    private var purchasedSeats = 0.0
    private var income = 0.0

    /**
     * This method initializes the seats in the cinema
     * seatState property serves as a state / a storage for
     * the seats
     *
     * @author Georgi Naumov
     *  gonaumov@gmail.com for contacts and suggestions
     */
    private fun initSeats(rows: Int, seats: Int): MutableList<MutableList<String>> {
        return MutableList(rows) {
            MutableList(seats) {
                "S"
            }
        }
    }

    /**
     * A method for calculating total seats
     *
     * @author Georgi Naumov
     *  gonaumov@gmail.com for contacts and suggestions
     */
    private fun calculateTotalSeats(rows: Int, seats: Int): Int {
        return rows * seats
    }

    /**
     * This method displays the seats in the cinema in such shape:
     *  Cinema:
     *   1 2 3 4 5 6
     *   1 B S S S S S
     *   2 S S S S S S
     *   3 S S S S S S
     *   4 S S S B S S
     *   5 S S S S S S
     *   6 S S S S S S
     *
     * @author Georgi Naumov
     *  gonaumov@gmail.com for contacts and suggestions
     */
    fun showSeats() {
        println("\nCinema:")
        print(" ")
        for (i in 1..seatState.first().size) {
            print(" $i")
        }
        println()
        seatState.forEachIndexed { index, strings ->
            print("${index + 1} ")
            strings.forEach {
                print("$it ")
            }
            println()
        }
        println()
    }

    /**
     * This method marks a place inside the cinema and can throw
     *  AlreadyPurchasedTicketException and IndexOutOfBoundsException
     *
     * @author Georgi Naumov
     *  gonaumov@gmail.com for contacts and suggestions
     */
    fun markPlace(rowNumber: Int, seatNumber: Int) {
        if (seatState[rowNumber.dec()][seatNumber.dec()] == "B") {
            throw AlreadyPurchasedTicketException("That ticket has already been purchased!")
        }
        seatState[rowNumber.dec()][seatNumber.dec()] = "B"
        purchasedSeats++
        income += calculateTicketPrice(rowNumber)
    }

    /**
     *  This method displays statistics about this cinema
     *   The number of purchased tickets;
     *    The number of purchased tickets represented as a percentage.
     *    Percentages should be rounded to 2 decimal places;
     *  Current income;
     *  The total income that shows how much money the theatre will get if all the tickets are sold.
     *
     * @author Georgi Naumov
     *  gonaumov@gmail.com for contacts and suggestions
     */
    fun showStatistics() {
        println("Number of purchased tickets: ${purchasedSeats.toInt()}")
        println("Percentage: ${"%.2f".format(purchasedSeats / totalSeats * 100)}%")
        println("Current income: $${income.toInt()}")
        println("Total income: $${calculateTotalPrice()}")
    }

    /**
     * This method calculates ticket price
     * Please see calculateTotalPrice method documentation for
     * more details.
     */
    fun calculateTicketPrice(rowNumber: Int): Int {
        val rows = seatState.size
        val seats = seatState.first().size
        val totalSeats = rows * seats
        return if (totalSeats < 60) {
            PRICE_FOR_LESS_THAN_SIXTY
        } else {
            val firstHalfRow = rows / 2
            if (rowNumber <= firstHalfRow) {
                PRICE_FOR_LESS_THAN_SIXTY
            } else {
                PRICE_FOR_MORE_THAN_SIXTY
            }
        }
    }

    /**
     *  This method calculated total price of the ticket. In fact the total
     *  profit which the cinema can accumulate for one movie.
     *   If the total number of seats in the screen room is not more than 60,
     *   then the price of each ticket is 10 dollars.
     *  In a larger room, the tickets are 10 dollars for the front half of the
     *  rows and 8 dollars for the back half. Please note that the number of rows can be odd,
     *  for example, 9 rows. In this case, the first half is the first 4 rows,
     *  and the second half is the rest 5 rows.
     */
    private fun calculateTotalPrice(): Int {
        return if (totalSeats < 60) {
            totalSeats * PRICE_FOR_LESS_THAN_SIXTY
        } else {
            val firstHalfRow = rows / 2
            val secondHalfRows = rows - firstHalfRow
            (firstHalfRow * seats * PRICE_FOR_LESS_THAN_SIXTY) + (secondHalfRows * seats * PRICE_FOR_MORE_THAN_SIXTY)
        }
    }

    /**
     * Generic method for printing menu
     */
    fun printMenu() {
        println("""
            1. Show the seats
            2. Buy a ticket
            3. Statistics
            0. Exit
        """.trimIndent())
    }
}