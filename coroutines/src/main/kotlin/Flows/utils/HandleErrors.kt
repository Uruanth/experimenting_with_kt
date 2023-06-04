package Flows.utils

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.runBlocking

fun main() {
    val hr = HandleErrors()
    hr.exceptionHandler()
}

@OptIn(InternalCoroutinesApi::class, FlowPreview::class, ExperimentalCoroutinesApi::class)
class HandleErrors {

    private val db = ColorDBMock()
    fun exceptionHandler() {
        runBlocking {
            db.getFlowWithError()
                .catch { e ->
                    println("error1: ${e.message}")
                }
                .flatMapConcat { int ->
                    println("int: $int")
                    db.getOtherFlow()
                        .catch { e ->
                            println("error2: ${e.message}")
                        }
                }
                .flatMapLatest { db.getFlowWithError() }
                .catch { e -> println("error3: ${e.message}") }
                .collect { println(it) }
        }
    }

}