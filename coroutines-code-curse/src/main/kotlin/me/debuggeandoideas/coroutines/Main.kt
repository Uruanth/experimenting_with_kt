package me.debuggeandoideas.coroutines

import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() {

    runBlocking {
        val time = measureTimeMillis {
            val userClient = UserApiClient()
            val creditApiClient = CreditApiClient()
            val post = PostData()
            for (i in 1..5) {
                val user = userClient.getClientByIdAsync(i)
                val userWithDebts = creditApiClient.getUserWithDebtsAsync(user)
                println(user)
                println(userWithDebts)
                post.postBBVA(userWithDebts)
                post.postBlueBank(userWithDebts)
                post.postInternationalBank(userWithDebts)
            }
        }
        println("Total " + time / 1000 + " seconds")
    }

}

