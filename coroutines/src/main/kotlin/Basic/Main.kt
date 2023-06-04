package Basic

import kotlinx.coroutines.*

fun main(args: Array<String>) {
    runBlocking {
        operation(2, 5)
    }
}

suspend fun operation(numA: Int, numB: Int) {
    withContext(Dispatchers.Default) {
        println("Resultado ${numA + numB}")
    }
}