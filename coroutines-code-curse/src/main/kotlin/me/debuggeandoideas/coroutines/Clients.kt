package me.debuggeandoideas.coroutines

import kotlinx.coroutines.*

class UserApiClient {
    suspend fun getClientById(id: Int): User {
        ClientUtil.randomDelay()
        Data.users.forEach { u ->
            if (u.id == id) {
                return u
            }
        }
        throw IllegalArgumentException("Invalid id")
    }

    suspend fun getClientByIdAsync(id: Int): User {
        return CoroutineScope(Dispatchers.Default).async {
            getClientById(id)
        }.await()
    }
}

class CreditApiClient {
    suspend fun getUserWithDebts(user: User): UserDebts {
        ClientUtil.randomDelay()
        val id = user.id
        if (Data.debts.containsKey(id)) {
            return UserDebts(id, Data.debts[id])
        }
        throw IllegalArgumentException("Invalid id")
    }

    suspend fun getUserWithDebtsAsync(user: User): UserDebts{
        return CoroutineScope(Dispatchers.Default).async {
            getUserWithDebts(user)
        }.await();
    }

}

class PostData {

    fun postInternationalBank(user: UserDebts) {
        val job = CoroutineScope(Dispatchers.Default).launch {
            ClientUtil.randomDelay()
            ClientUtil.postUser(user, "International Bank")
        }

    }

    fun postBBVA(user: UserDebts) {
        val job = CoroutineScope(Dispatchers.Default).launch {
            ClientUtil.randomDelay()
            ClientUtil.postUser(user, "BBVA")
        }
    }

    fun postBlueBank(user: UserDebts) {
        val job = CoroutineScope(Dispatchers.Default).launch {
            ClientUtil.randomDelay()
            ClientUtil.postUser(user, "blue bank")
        }
    }
}

class ClientUtil {

    companion object {

        suspend fun randomDelay() {
            val sleep = (1..3).random().toLong();
            delay(sleep*1000);
        }

        fun postUser(user: UserDebts, bank: String) {
            println("Posting in $bank with id: ${user.id}")
        }
    }

}