package Basic

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

fun main() {
    val a = Channels();
    a.startProducerAndCosumer();
}

class Channels() {
    //Channel puede recibin un arg en su constructor que indica la capacidad que posee
    private val channel = Channel<Notes2>();
    private var produced = 0;
    private var consumed = 0;
    private var finished = false;
    private val batch = IntArray(5);

    private val mutex = Mutex();

    fun startProducerAndCosumer(): Unit {
        runBlocking {
            //Producer
            launch(Dispatchers.Default) {
                val producers = List(100_000) {
                    launch {
                        val note = ChannelsUtil2.getRandomNote();
                        channel.send(note);
                        mutex.withLock {
                            produced++;
                        }
                    }
                }
                producers.joinAll();
                //TODO no olvidar cerrar los channel, ya que son un buffer
                channel.close();
                finished = true;

                println("Producer done");
            }

            //Consumer
            launch(Dispatchers.Default) {
                val consumers = List(batch.size) {
                    launch {
                        for (note in channel) {
                            mutex.withLock {
                                consumed++;
                                batch[it];
                            }
                        }

                    }
                }


                consumers.joinAll();
                println("Consumer finished")

            }
        }

        println("Notes produced: $produced")
        println("Notes consumed: $consumed")

        val total = batch.reduce { acc, i ->
            acc + i
        }

        println("total: $total");
    }
}


class ChannelsUtil2() {

    companion object {

        private val notes = Notes2.values()

        fun getRandomNote(): Notes2 {

            val pos = (0..6).random()

            return notes[pos]

        }

    }

}

enum class Notes2() { DO, RE, MI, FA, SOL, LA, SI }