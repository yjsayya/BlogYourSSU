package com.example.blogyourssu.dto.res.article

import com.example.blogyourssu.domain.Article

data class ArticleCreateResDto (

    private val articleId: Long? = null,
    private val email: String? = null,
    private val title: String? = null,
    private val content: String? = null,

){

    companion object {
        fun entityToDto(entity: Article): ArticleCreateResDto {
            return ArticleCreateResDto(
                entity.id,
                entity.user?.email,
                entity.title,
                entity.content
            )
        }
    }


} // class