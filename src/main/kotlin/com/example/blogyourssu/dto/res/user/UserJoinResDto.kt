package com.example.blog.dto.res.user

import com.example.blog.domain.User

data class UserJoinResDto (
    private val email: String? = null,
    private val username: String? = null
) {


    companion object {
        fun entityToDto(entity: User): UserJoinResDto {
            return UserJoinResDto(entity.email, entity.username)
        }
    }

}