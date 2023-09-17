package com.example.blogyourssu.repository

import com.example.blogyourssu.domain.Article
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ArticleRepository : JpaRepository<Article?, Long?> {
    fun findByTitle(title: String?): Optional<Article?>?
}