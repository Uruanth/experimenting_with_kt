package Flows.utils

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() {
    val flat = FlatOperators()
//    flat.flapMapConcat()
//    flat.flatMapMerge()
//    flat.flatMapLatest()
//    flat.zipOperator()
    flat.combine()
}

@OptIn(InternalCoroutinesApi::class, FlowPreview::class, ExperimentalCoroutinesApi::class)
class FlatOperators {

    val db = ColorDBMock()

    //Retorna otro flujo
    fun flapMapConcat() {
        runBlocking {
            db.getFlow()
                .flatMapConcat { color ->
                    db.getFlowDelay(color)
                }
                .collect { println(it) }
        }
    }

    //Combina los flujos y va emitiendo sin necesitad de esperar que alguno se complete
    fun flatMapMerge() {
        runBlocking {
            db.getFlow()
                .flatMapMerge { color ->
                    db.getFlowDelay(color)
                }
                .collect { println(it) }
        }
    }

    //Completa los flujos y a medida que llegan y empieza a emitir uno nuevo
    fun flatMapLatest() {
        runBlocking {
            db.getFlow()
                .flatMapLatest { color ->
                    db.getFlowDelay(color)
                }
                .collect { println(it) }
        }
    }

    //Une dos flujos, y emite un resultado por cada par de valores emitidos, si un flujo emite mas elementos que el otro, estos valores son omitidos
    fun zipOperator() {
        val letterFlow = flowOf("a", "b", "d", "c")
        runBlocking {
            letterFlow
                .zip(flowOf("azul", "negro", "verde", "blanco")) { letter, color ->
                    "$letter $color"
                }.collect { println(it) }

        }
    }

    //Si un flujo emite mas elementos que el otro, se combina con el ultimo elemento emitido por el otro flujo
    fun combine() {
        val letterFlow = flowOf("a", "b", "d", "c")
        runBlocking {
            letterFlow
                .combine(flowOf("azul", "negro", "verde")) { letter, color ->
                    "$letter $color"
                }.collect { println(it) }

        }
    }

}