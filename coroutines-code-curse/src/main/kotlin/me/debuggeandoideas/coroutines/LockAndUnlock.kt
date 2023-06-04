package me.debuggeandoideas.coroutines

class ChannelsUtil() {

    companion object {

        private val notes = Notes.values()

        fun getRandomNote(): Notes {

            val pos = (0..6).random()

            return notes[pos]

        }

    }

}

enum class Notes() { DO, RE, MI, FA, SOL, LA, SI}