package com.perry.biblioteca.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.WebFluxConfigurer
import org.springframework.web.reactive.config.WebFluxConfigurerComposite

@Configuration
class WebFluxConfig {
    @Bean
    fun corsConfigure(): WebFluxConfigurer {
        return object : WebFluxConfigurerComposite() {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**").allowedOrigins("*")
                    .allowedMethods("*")
            }
        }
    }
}