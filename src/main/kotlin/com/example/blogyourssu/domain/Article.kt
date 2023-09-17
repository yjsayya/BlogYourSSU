package com.example.blogyourssu.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDate
import java.util.*
import javax.persistence.*

@EntityListeners(AuditingEntityListener::class)
@Entity
data class Article (

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="article_id")
    var id: Long?,

    @Column(nullable = false)
    var title: String,

    @Column(nullable = false)
    var content: String,

) {

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User? = null

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val commentList: MutableList<Comment> = mutableListOf()

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDate? = null

    @LastModifiedDate
    @Column(name = "updated_at")
    val updatedAt: LocalDate? = null



    protected constructor() : this(null,"","",)

    companion object {
        fun of(title: String, content: String, user: User): Article {
            return Article(null,title, content)
        }
    }


}