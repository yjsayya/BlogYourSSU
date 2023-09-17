package com.example.blogyourssu.service

import com.example.blogyourssu.domain.Comment
import com.example.blogyourssu.domain.Comment.Companion.of
import com.example.blogyourssu.domain.User
import com.example.blogyourssu.dto.res.comment.CommentCreateResDto
import com.example.blogyourssu.dto.res.comment.CommentUpdateResDto
import com.example.blogyourssu.error.BlogApplicationException
import com.example.blogyourssu.error.ErrorCode
import com.example.blogyourssu.repository.ArticleRepository
import com.example.blogyourssu.repository.CommentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class CommentService (

    private val commentRepository: CommentRepository? = null,
    private val articleRepository: ArticleRepository? = null,
    private val userService: UserService? = null

){

    /**
     * 댓글 등록
     */
    @Transactional
    open fun create(articleId: Long, content: String?, email: String?, password: String?): CommentCreateResDto {
        // 1. User 인증
        val user = userService!!.validateUser(email, password)
        // 2. Article 찾기
        val article = articleRepository!!.findById(articleId)
            .orElseThrow { BlogApplicationException(ErrorCode.ARTICLE_NOT_FOUND) }!!
        // 2. Comment - 유효성 검사
        validateComment(content)

        // 3. 댓글 등록 진행 진행시켜
        val entity = commentRepository!!.save(of(content!!, user!!, article!!))
        return CommentCreateResDto.entityToDto(entity)
    }

    /**
     * 댓글 수정
     */
    @Transactional
    open fun update(commentId: Long, content: String?, email: String?, password: String?): CommentUpdateResDto {
        // 1. User 인증
        val user = userService!!.validateUser(email, password)
        // 2. Comment - 유효성 검사
        validateComment(content)
        // 3. Comment - 존재 여부 확인
        val comment = getComment(commentId)
        // 4. Comment - 권한 확인
        checkCommentPermission(user, comment)

        // 5. 댓글 수정 진행시켜
        comment.content = content
        return CommentUpdateResDto.entityToDto(comment)
    }

    /**
     * 댓글 삭제
     */
    fun delete(commentId: Long, email: String?, password: String?) {
        // 1. User 인증
        val user = userService!!.validateUser(email, password)
        // 2. Comment - 존재 여부 확인
        val comment = getComment(commentId)
        // 3. Comment - 권한 확인
        checkCommentPermission(user, comment)

        // 3. 댓글 삭제 진행
        commentRepository!!.delete(comment)
    }

    private fun validateComment(content: String?) {
        if (content == null || content == "" || content == " ") throw BlogApplicationException(ErrorCode.EMPTY_COMMENT)
    }

    private fun getComment(commentId: Long): Comment {
        return commentRepository!!.findById(commentId)
            .orElseThrow { BlogApplicationException(ErrorCode.COMMENT_NOT_FOUND) }!!
    }

    private fun checkCommentPermission(user: User?, comment: Comment) {
        if (user?.id != comment.user?.id)
            throw BlogApplicationException(ErrorCode.COMMENT_PERMISSION_DENIED)
    }


}