package com.example.blogyourssu.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDate
import javax.persistence.*

@EntityListeners(AuditingEntityListener::class)
@Entity
data class User (

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    var id: Long?,

    @Column(nullable = false)
    var email: String,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = false)
    var username: String,

) {

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val articles: MutableList<Article> = mutableListOf()

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val comments: MutableList<Comment> = mutableListOf()

    @CreatedDate
    @Column(name="created_at", nullable=false, updatable=false)
    var createdAt : LocalDate? = null

    @LastModifiedDate
    @Column(name="updated_at")
    var updatedAt : LocalDate?  = null


    protected constructor() : this(null, "","","")

    companion object {
        fun of(email: String, password: String, username: String): User {
            return User(null,email,password ,username)
        }
    }


}