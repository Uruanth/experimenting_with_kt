import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext

fun main() {
    createOwnContext()
}

fun createOwnContext() {
    runBlocking(CoroutineName("First coroutine")) {
        println(
            """
            First contex:
            Job: ${this.coroutineContext[Job]}
            ContinuationInterceptor: ${this.coroutineContext[ContinuationInterceptor]}
            CoroutineExceptionHandler: ${this.coroutineContext[CoroutineExceptionHandler]}
            CoroutineName: ${this.coroutineContext[CoroutineName]}
        """.trimIndent()
        )

        val newContext: CoroutineContext = this.coroutineContext + CoroutineName("Second coroutine")
        println(
            """
                
            Second  contex:
            Job: ${newContext[Job]}
            ContinuationInterceptor: ${newContext[ContinuationInterceptor]}
            CoroutineExceptionHandler: ${newContext[CoroutineExceptionHandler]}
            CoroutineName: ${newContext[CoroutineName]}
        """.trimIndent()
        )
    }
}