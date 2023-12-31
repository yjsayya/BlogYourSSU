package com.example.blogyourssu.dto.res.comment

import com.example.blogyourssu.domain.Comment

data class CommentCreateResDto (
    private val commentId: Long? = null,
    private val email: String? = null,
    private val content: String? = null

){

    companion object {
        fun entityToDto(entity: Comment): CommentCreateResDto {
            return CommentCreateResDto(
                entity.id,
                entity.user?.email,
                entity.content
            )
        }
    }


}