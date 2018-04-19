package main.kotlin.gyor.meetup

import main.java.gyor.meetup.JavaMoney
import java.math.BigDecimal

data class Money(val amount: BigDecimal, val currency: String)

fun sendPayment(money: Money, message: String = "Hello!") {
    println("$message Sending ${money.amount}${money.currency}...")
}

fun sum(x: Int, y: Int) = x + y

infix fun Int.percentOf(money: Money) = money.amount.multiply(BigDecimal(this)).divide(BigDecimal(100))

fun convertToDollars(money: Money) = when (money.currency) {
    "$" -> money
    "EUR" -> Money(money.amount * BigDecimal(1.10), "$")
    else ->throw IllegalArgumentException("Invalid currency")
}

fun javaMoney(money: Money?) {
    println("${money?.amount} is cool!")
}

fun findEmails(users: List<User>, predicate: (String) -> (Boolean)): List<User> {
    TODO("Later")
}

fun main(args: Array<String>) {



    val ticketPrice = Money(100.bd, "$")
    val popcornPrice = ticketPrice.copy(BigDecimal(200), "EUR");

    println(ticketPrice == popcornPrice)

    val javaMoney = JavaMoney(100, "$")
    javaMoney.amount

    sendPayment(message = "Szia", money = ticketPrice)

    println(7 percentOf ticketPrice)

    val lng = 100L
    val bigD = 100.bd

    val users = getSomeUsers()
//    findEmails(users) {
//        it.endsWith(".com")
//    }

    println(users.filter { it.email.endsWith(".com")}
            .sortedBy { it.id }
            .map { Pair(it.username, it.email)})

}

private val Int.bd: BigDecimal
    get() = BigDecimal(this)
