package me.debuggeandoideas.coroutines

class Data {

    companion object {
        val users = setOf(
            User(1, "Mark", "Zuckerberg", 38),
            User(2, "Bill", "Gates", 67),
            User(3, "Jeff", "Bezos", 58),
            User(4, "Elon", "Musk", 50),
            User(5, "Timothy", "Donald", 62)
        )

        val debts = mapOf(
            1 to false,
            2 to false,
            3 to true,
            4 to false,
            5 to true
        )
    }
}