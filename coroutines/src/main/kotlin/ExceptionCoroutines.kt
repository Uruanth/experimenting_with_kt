import kotlinx.coroutines.*

fun main(){
    runBlocking {
        try {
            pow(2)

        } catch (e: TimeoutCancellationException){
            println("timeout coroutine")
        }
    }
}

suspend fun pow(d : Long){
    coroutineScope {
        var result = d
        withTimeout(3_000){
            //TODO launch no regresa ningun valor
            launch {
                repeat(50){
                    result *= d
                    println(result)
                    delay(100)
                }
            }
        }
    }
}