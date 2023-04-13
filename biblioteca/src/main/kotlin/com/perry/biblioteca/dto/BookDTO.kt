package com.perry.biblioteca.dto

import java.time.LocalDate

class BookDTO() {

    var id: String? = null
        set(value){
            if (value === null){
                throw RuntimeException("el valor no puede ser nulo")
            }
            field = value
        }
    var name: String? = null
    var state: Boolean? = null
    var loanDate: LocalDate? = null
    var type: String? = null
    var thematic: String? = null

    constructor(
        name: String
    ) : this() {
        this.name = name
    }

    constructor(
        id: String,
        name: String,
        state: Boolean,
        loanDate: LocalDate,
        type: String,
        thematic: String
    ) : this(name) {
        this.id = id
        this.state = state
        this.loanDate = loanDate
        this.type = type
        this.thematic = thematic
    }

    override fun toString(): String {
        return "BookDTO(id=$id, " +
                "name=$name, " +
                "state=$state, " +
                "loanDate=$loanDate, " +
                "type=$type, " +
                "thematic=$thematic)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BookDTO

        if (id != other.id) return false
        if (name != other.name) return false
        if (state != other.state) return false
        if (loanDate != other.loanDate) return false
        if (type != other.type) return false
        return thematic == other.thematic
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (state?.hashCode() ?: 0)
        result = 31 * result + (loanDate?.hashCode() ?: 0)
        result = 31 * result + (type?.hashCode() ?: 0)
        result = 31 * result + (thematic?.hashCode() ?: 0)
        return result
    }


}