package com.example.blogyourssu.dto.res.user

import com.example.blogyourssu.domain.User

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