package com.example.blogyourssu.repository

import com.example.blogyourssu.domain.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment?, Long?>