package Basic

import kotlinx.coroutines.*
import java.util.LinkedList

fun main() {
    myJob()
}

//TODO cuando se necesita ejecutar una tarea en segundo plano sin necesidad de retornar un valor
//TODO Ejemplo: procesos batch

fun myJob() {
    val contries = listOf(
        "Mexico", "Canada", "Noruega"
    )

    val colors = listOf(
        "red", "blue", "green"
    )

    val numbersList = listOf(
        "one", "two", "three", "four"
    )

    val results: LinkedList<String> = LinkedList()
    val coroutines: LinkedList<String> = LinkedList()

    runBlocking {
        withTimeout(50_000) {
            //TODO Job padre, guardar en una variable la couritina de launch
            val jobMaster = launch(CoroutineName("Master")) {
                //TODO job hijo
                val coutrieJob = launch(CoroutineName("Countries")) {
                    for (c in contries) {
                        results.add(c)
                        delay(100)
                    }

                    this.coroutineContext[CoroutineName]?.let {
                        coroutines.add(it.name)
                    }
                }

                val numberJob = launch(CoroutineName("Numbers")) {
                    for (n in numbersList) {
                        results.add(n)
                        delay(100)
                    }
                    this.coroutineContext[CoroutineName]?.let {
                        coroutines.add(it.name)
                    }
                }
                val colorsJob = launch(CoroutineName("Colors")) {
                    for (c in colors) {
                        results.add(c)
                        delay(100)
                    }
                    this.coroutineContext[CoroutineName]?.let {
                        coroutines.add(it.name)
                    }
                }


                val coroutineKiller = (1..4).random()
                println("CoroutineKiller: $coroutineKiller")

                when (coroutineKiller) {
                    1 -> {
                        coutrieJob.cancel()
                        println("Countrie canceled")
                    }

                    2 -> {
                        colorsJob.cancel()
                        println("Colors canceled")
                    }

                    3 -> {
                        numberJob.cancel()
                        println("numbers canceled")
                    }

                    else -> println("try again")

                }
            }


//            TODO el join espera que se termine el Job
            jobMaster.join()
            println(jobMaster.isCompleted)
            println(results)
            println(coroutines)

        }
    }
}