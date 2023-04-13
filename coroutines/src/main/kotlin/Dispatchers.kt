import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking(Dispatchers.Unconfined) {
        println("Using thread: ${Thread.currentThread()}")
    }

    runBlocking(Dispatchers.Default) {
        println("Using thread: ${Thread.currentThread()}")
    }

    runBlocking(Dispatchers.IO) {
        println("Using thread: ${Thread.currentThread()}")
    }

//   TODO Solo para desarrollo Android
//    runBlocking(Dispatchers.Main) {
//        println("Using thread: ${Thread.currentThread()}")
//    }
}