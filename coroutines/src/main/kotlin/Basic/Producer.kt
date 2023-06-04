package Basic

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlin.coroutines.CoroutineContext

fun main() {
    println("fun main")
    val colorP = ColorProducer();
    val colorC = ColorConsumer();
    runBlocking {
        launch {
            colorP.produceStream();
        }

        launch {
            colorC.consumeStream(colorP.getChannel())
            colorC.released();
        }

    }
 }


class ColorProducer(private val dispatcher: CoroutineDispatcher = Dispatchers.Default) {
    private val channel = Channel<Colors>();
    fun getChannel(): ReceiveChannel<Colors> = this.channel;

    suspend fun produceStream() = withContext(dispatcher) {
        ColorsUtil.getListColors().forEach { color ->
            channel.send(color);
        }
        channel.close();
    }


}

class ColorConsumer(private val dispatcher: CoroutineDispatcher = Dispatchers.Default) : CoroutineScope {
    private val job = Job();
    override val coroutineContext: CoroutineContext
        get() = job + dispatcher

    suspend fun consumeStream(colors: ReceiveChannel<Colors>) = withContext(dispatcher) {
        colors
            .filterByPrimary()
            .consumeEach { color: Colors -> println(color) }
    }


    fun released(){
        this.job.cancel();
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun ReceiveChannel<Colors>.filterByPrimary(): ReceiveChannel<Colors> = produce {
        consumeEach { color: Colors ->
            if (!color.isPrimay){
                send(color)
            }
        }
    }
}

data class Colors(
    val name: String,
    val hexadecimal: String,
    val isPrimay: Boolean,
    val rgb: String
)

class ColorsUtil {
    companion object {
        fun getListColors(): List<Colors> {
            return listOf(
                Colors("blue", "#09372", false, "0.4.3"),
                Colors("red", "#7567567", true, "10.43.31"),
                Colors("ff", "#04452", false, "20.34.223"),
                Colors("beeue", "#09355", true, "220.204.3"),
            )
        }
    }
}
