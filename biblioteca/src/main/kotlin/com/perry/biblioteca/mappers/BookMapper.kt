package com.perry.biblioteca.mappers

import com.perry.biblioteca.dto.BookDTO
import com.perry.biblioteca.model.Book
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.util.function.Function

@Component
class BookMapper {
    fun mapperToBook(id: String?): Function<BookDTO, Book> {
        return Function<BookDTO, Book> { updateBook: BookDTO ->
            Book(
                id,
                updateBook.name!!,
                updateBook.state!!,
                updateBook.loanDate ?: LocalDate.now(),
                updateBook.type!!,
                updateBook.thematic!!
            )

        }
    }

    fun mapToDTO(): Function<Book, BookDTO> {
        return Function<Book, BookDTO> { entity: Book ->
            BookDTO(
                entity.id ?: "",
                entity.name ?: "",
                entity.state ?: false,
                entity.loanDate ?: LocalDate.now(),
                entity.type ?: "",
                entity.thematic ?: ""
            )
        }
    }
}
