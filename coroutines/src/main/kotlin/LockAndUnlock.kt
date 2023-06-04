import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.ConcurrentLinkedQueue

fun main() {
    val a = LockAndUnlock();
    a.startProducerAndCosumer();
}

class LockAndUnlock() {

    private val queue = ConcurrentLinkedQueue<Notes>();
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
                        val note = ChannelsUtil.getRandomNote();
                        if (queue.offer(note)) {
                            mutex.withLock {
                                produced++;
                            }
                        }
                    }
                }
                producers.joinAll();
                finished = true;

                println("Producer finished");
            }

            //Consumer
            launch(Dispatchers.Default) {
                val consumers = List(batch.size) {
                    launch {
                        while (!finished || queue.isNotEmpty()) {
                            if (queue.isNotEmpty()) {
                                val note = queue.poll();
                                if (note != null) {
                                    mutex.withLock {
                                        consumed++;
                                        batch[it]++;
                                    }
                                }
                            }
                        }
                    }
                }


                consumers.joinAll();
                println("Consumer finished")

            }
        }

        println("Queue size: ${queue.size}");
        println("Notes produced: $produced")
        println("Notes consumed: $consumed")

        val total = batch.reduce { acc, i ->
            acc + i
        }

        println("total: $total");
    }
}


class ChannelsUtil() {

    companion object {

        private val notes = Notes.values()

        fun getRandomNote(): Notes {

            val pos = (0..6).random()

            return notes[pos]

        }

    }

}

enum class Notes() { DO, RE, MI, FA, SOL, LA, SI }