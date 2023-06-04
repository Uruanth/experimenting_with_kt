package me.debuggeandoideas.coroutines

data class User(val id: Int, val name: String, val lastName: String, val age: Int)

data class UserDebts(val id: Int, val hasDebts: Boolean? = false)