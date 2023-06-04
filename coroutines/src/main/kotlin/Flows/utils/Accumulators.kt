package Flows.utils

import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking

fun main() {
    val acc = Accumulators()
    acc.reduceAcc()
    acc.foldAcc()
    acc.singleAcc()
}

class Accumulators {

    val db = ColorDBMock();

    //El acumulador empieza en 0
    fun reduceAcc() {
        runBlocking {
            val reduce = db.getFlow()
                .map { it.id }
                .reduce { accumulator, value ->
                    value + accumulator
                }

            println(reduce)
        }
    }

    //El fold nos permite un valor inicial a diferencia del reduce
    fun foldAcc() {
        runBlocking {
            val fold = db.getFlow()
                .map { it.id }
                .fold(100) { accumulator, value ->
                    value + accumulator
                }
            println(fold)
        }
    }

    //Si el flujo emite mas de n valor, lanza una excepcion
    fun singleAcc() {
        runBlocking {
            val single = db.getFlow()
                .single()

            println(single)
        }
    }

}