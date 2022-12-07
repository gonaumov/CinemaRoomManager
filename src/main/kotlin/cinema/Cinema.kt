package cinema

/**
 *  An implementation of Cinema Manager JetBrains academy project:
 *  https://hyperskill.org/projects/138
 *
 *  @author Georgi Naumov
 *  gonaumov@gmail.com for contacts and suggestions
 */
fun main(args: Array<String>) {
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val seats = readln().toInt()
    val cinemaManager = CinemaManager(rows, seats)
    var userChoice: Int
    do {
        cinemaManager.printMenu()
        userChoice = readln().toInt()
        when (userChoice) {
            1 -> cinemaManager.showSeats()
            2 -> {
                var purchasesSuccessFull = false
                do {
                    var thereIsAnError = false
                    println("Enter a row number:")
                    val rowNumber = readln().toInt()
                    println("Enter a seat number in that row:")
                    val seatNumber = readln().toInt()
                    println("Ticket price: $${cinemaManager.calculateTicketPrice(rowNumber)}")
                    try {
                        cinemaManager.markPlace(rowNumber, seatNumber)
                    } catch (ex: AlreadyPurchasedTicketException) {
                        println(ex.message)
                        thereIsAnError = true
                    } catch (ex: IndexOutOfBoundsException) {
                        println("Wrong input!")
                        thereIsAnError = true
                    }
                    if (!thereIsAnError) {
                        purchasesSuccessFull = true
                    }
                } while (!purchasesSuccessFull)
            }
            3 -> cinemaManager.showStatistics()
        }
    } while (userChoice != 0)
}