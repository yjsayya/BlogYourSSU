package com.example.blogyourssu.repository

import com.example.blogyourssu.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User?, Long?> {
    fun findByEmail(email: String?): Optional<User?>?
}