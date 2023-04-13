package com.perry.biblioteca.repository

import com.perry.biblioteca.model.Book
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface BookRepository : ReactiveMongoRepository<Book, String> {

    fun findByThematic(thematic: String): Flux<Book>
    fun findByType(type: String): Flux<Book>
}