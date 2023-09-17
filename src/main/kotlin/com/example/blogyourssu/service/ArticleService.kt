package com.example.blogyourssu.service

import com.example.blogyourssu.domain.Article
import com.example.blogyourssu.domain.Article.Companion.of
import com.example.blogyourssu.domain.User
import com.example.blogyourssu.dto.res.article.ArticleCreateResDto
import com.example.blogyourssu.dto.res.article.ArticleUpdateResDto
import com.example.blogyourssu.error.BlogApplicationException
import com.example.blogyourssu.error.ErrorCode
import com.example.blogyourssu.repository.ArticleRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class ArticleService (
    private val articleRepository: ArticleRepository? = null,
    private val userService: UserService? = null
){

    /**
     * 게시글 등록
     */
    @Transactional
    open fun create(title: String?, content: String?, email: String?, password: String?): ArticleCreateResDto {
        // 1. User 인증
        val user = userService!!.validateUser(email, password)
        // 2. Article - 유효성 검사
        validateArticle(title, content)

        // 3. 게시글 등록 진행시켜
        val entity = articleRepository!!.save(of(title!!, content!!, user!!))
        return ArticleCreateResDto.entityToDto(entity)
    }

    /**
     * 게시글 수정
     */
    @Transactional
    open fun update(
        articleId: Long,
        title: String?,
        content: String?,
        email: String?,
        password: String?
    ): ArticleUpdateResDto {
        // 1. User 인증
        val user = userService!!.validateUser(email, password)
        // 2. Article 찾기
        val article = getArticle(articleId)
        // 3. Article - 권한 검사
        checkArticlePermission(user, article)
        // 4. Article - 유효성 검사
        validateArticle(title, content)

        // 4. 게시글 수정 진행 시켜
        if (title != null)
            article.title = title

        if (content != null)
            article.content = content

        return ArticleUpdateResDto.entityToDto(article)
    }

    /**
     * 게시글 삭제
     */
    fun delete(articleId: Long, email: String?, password: String?) {
        // 1. User 인증
        val user = userService!!.validateUser(email, password)
        // 2. Article 찾기
        val article = getArticle(articleId)
        // 3. Article - 권한 검사
        checkArticlePermission(user, article)

        // 3. 게시글 삭제 진행시켜
        articleRepository!!.delete(article)
    }

    private fun validateArticle(title: String?, content: String?) {
        // 1. 유효성 검사 - 게시글's 제목
        if (title == null || title == "" || title == " ")
            throw BlogApplicationException(ErrorCode.EMPTY_ARTICLE_TITLE)
        // 2. 유효성 검사 - 게시글's 내용
        if (content == null || content == "" || content == " ")
            throw BlogApplicationException(ErrorCode.EMPTY_ARTICLE_CONTENT)
    }

    private fun getArticle(articleId: Long): Article {
        return articleRepository!!.findById(articleId)
            .orElseThrow { BlogApplicationException(ErrorCode.ARTICLE_NOT_FOUND) }!!
    }

    private fun checkArticlePermission(user: User?, article: Article) {
        if (user?.id != article.user?.id)
            throw BlogApplicationException(ErrorCode.ARTICLE_PERMISSION_DENIED)
    }



}