package com.perry.biblioteca.routers

import com.perry.biblioteca.dto.BookDTO
import com.perry.biblioteca.usecases.UCCreate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RequestPredicates.POST
import org.springframework.web.reactive.function.server.RequestPredicates.accept
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions.route
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Configuration
class BookRouter {

    @Bean
    fun createBook(useCase: UCCreate): RouterFunction<ServerResponse> {

        var executor1: Function<Mono<ServerResponse>> = { bookDto: BookDTO ->
            useCase.apply(bookDto)
                .flatMap { resultado ->
                    ServerResponse.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .bodyValue(resultado)
                }
        }



        return route(
            POST("/create")
                .and(accept(MediaType.APPLICATION_JSON))
        )
        { request: ServerRequest ->
            request.bodyToMono(BookDTO::class.java)
                .flatMap { bookDto: BookDTO ->
                    useCase.apply(bookDto)
                        .flatMap { resultado ->
                            ServerResponse.ok()
                                .contentType(MediaType.TEXT_PLAIN)
                                .bodyValue(resultado)
                        }
                }
        }
    }

}