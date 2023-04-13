import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

fun main() {
    //No importa cual hilo sea
    runBlocking(Dispatchers.Unconfined) {
        println("Using thread Unconfined: ${Thread.currentThread()}")
    }

    //Mejorar performance, mas usado
    runBlocking(Dispatchers.Default) {
        println("Using thread Default: ${Thread.currentThread()}")
    }

    //Hilo para inputs/outputs
    runBlocking(Dispatchers.IO) {
        println("Using thread IO: ${Thread.currentThread()}")
    }


    //Hilo personalizado
    runBlocking(newSingleThreadContext("custom")) {
        println("Using thread custom: ${Thread.currentThread()}")
    }


//   TODO Solo para desarrollo Android
//    runBlocking(Dispatchers.Main) {
//        println("Using thread: ${Thread.currentThread()}")
//    }
}