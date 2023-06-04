import kotlinx.coroutines.runBlocking
import me.debuggeandoideas.coroutines.CreditApiClient
import me.debuggeandoideas.coroutines.PostData
import me.debuggeandoideas.coroutines.UserApiClient
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.system.measureTimeMillis

class CoroutinesSpec {

    val client = UserApiClient();
    val creditApiClient = CreditApiClient()
    val post = PostData()


    @Test
    fun getClientByIdAsynTest() {
        runBlocking {
            val response = client.getClientByIdAsync(1)
            Assertions.assertNotNull(response)
        }
    }

    @Test
    fun testPerformance() {
       runBlocking {
           val time = measureTimeMillis {
               for (i in 1..5) {
                   val user = client.getClientByIdAsync(i)
                   val userWithDebts = creditApiClient.getUserWithDebtsAsync(user)
                   println(user)
                   println(userWithDebts)
                   post.postBBVA(userWithDebts)
                   post.postBlueBank(userWithDebts)
                   post.postInternationalBank(userWithDebts)
               }
           }
           println("time: $time")
       }
    }

}