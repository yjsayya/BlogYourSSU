package com.example.blogyourssu.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.util.*

@Configuration
@EnableJpaAuditing
class JpaConfig {
    @Bean
    fun auditorAware(): AuditorAware<String> {
        return AuditorAware { Optional.of("sayya") }
        // TODO: spring security로 인증 기능을 구현했을때, 수정하자
    }
}