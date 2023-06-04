package Flows

import Flows.utils.ColorDBMock
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() {
    val color = ColorDBMock()
    val time = measureTimeMillis {
        runBlocking {
            color.getFlow().collect {
                println(it)
            }

            color.getOtherFlow().collect { int ->
                println(int)
            }
        }
    }
    println("total time: $time")
}