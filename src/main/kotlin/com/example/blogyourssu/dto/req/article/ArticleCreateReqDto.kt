package com.example.blogyourssu.dto.req.article

data class ArticleCreateReqDto(

    val email: String? = null,
    val password: String? = null,
    val title: String? = null,
    val content: String? = null

)