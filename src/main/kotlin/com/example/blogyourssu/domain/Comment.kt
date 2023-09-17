package com.example.blogyourssu.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDate
import java.util.*
import javax.persistence.*

@EntityListeners(AuditingEntityListener::class)
@Entity
data class Comment(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    val id: Long,

    @Column(nullable = false, length = 255)
    var content: String? = null,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User? = null,

    @ManyToOne
    @JoinColumn(name = "article_id")
    val article: Article? = null,

){

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDate? = null

    @LastModifiedDate
    @Column(name = "updated_at")
    val updatedAt: LocalDate? = null

    protected constructor() : this(0,"")

    companion object {
        fun of(content: String, user: User, article: Article): Comment {
            return Comment(id = 0, content)
        }
    }


}