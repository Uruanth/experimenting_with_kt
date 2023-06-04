package Flows.utils

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking


fun main() {
    val op = Operators()
    op.mapOperator()
    op.filterOperator()
    op.notFilterOperator()
    op.transform()
}


//Anotaciones para evitar errores al copilar
@OptIn(InternalCoroutinesApi::class, FlowPreview::class)
class Operators() {
    val db = ColorDBMock();

    fun mapOperator() {
        runBlocking {
            db.getFlow()
                .map { color ->
                    SimpleColor(color.name, color.hexa)
                }.collect {
                    println(it)
                }
        }
    }

    fun filterOperator() {
        runBlocking {
            db.getOtherFlow()
                .filter { int ->
                    int % 2 == 0
                }
                .collect { println(it) }
        }
    }

    fun notFilterOperator() {
        runBlocking {
            db.getOtherFlow()
                .filterNot { int ->
                    int % 2 == 0
                }
                .collect { println(it) }
        }
    }


    fun transform() {
        runBlocking {
            db.getFlow()
                .transform { color ->
                    if (color.isPrimary){
                        emit(color.hexa)
                    }
                }
                .collect { println(it) }
        }
    }


}