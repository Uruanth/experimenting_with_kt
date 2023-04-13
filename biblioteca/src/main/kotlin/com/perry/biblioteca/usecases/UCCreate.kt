package com.perry.biblioteca.usecases

import com.perry.biblioteca.dto.BookDTO
import com.perry.biblioteca.mappers.BookMapper
import com.perry.biblioteca.model.Book
import com.perry.biblioteca.repository.BookRepository
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated
import reactor.core.publisher.Mono


@Service
@Validated
class UCCreate(
    val bookRepositoy: BookRepository,
    val mapper: BookMapper
) : UCMono<Mono<String>, BookDTO>() {
    override fun apply(bookDTO: BookDTO): Mono<String> {
        return bookRepositoy.save(mapper.mapperToBook("").apply(bookDTO))
            .map(Book::id)
    }
}