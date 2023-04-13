package com.perry.biblioteca.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate


@Document("book")
class Book() {

    @Id
    lateinit var id: String

    var name: String? = null
    var state: Boolean? = null
    var loanDate: LocalDate? = null
    var type: String? = null
    var thematic: String? = null

    constructor(
        id: String?,
        name: String,
        state: Boolean,
        loanDate: LocalDate,
        type: String,
        thematic: String
    ) : this() {
        if (!id.isNullOrBlank()) {
            this.id = id
        }
        this.name = name
        this.state = state
        this.loanDate = loanDate
        this.type = type
        this.thematic = thematic
    }
}