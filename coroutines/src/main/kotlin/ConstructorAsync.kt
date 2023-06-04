import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main(){
    var foo = "";
    runBlocking {
        val deferred = async {
            getFoo("bar")
        }
        foo = deferred.await();
    }

    println(foo);
}

suspend fun getFoo(arg: String): String{
    delay(500);
    return when(arg){
        "foo" -> "Red"
        "bar" -> "Blue"
        else -> "Color not found"
    }
}