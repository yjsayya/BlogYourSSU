package com.example.blogyourssu.repository

import com.example.blog.domain.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment?, Long?> 