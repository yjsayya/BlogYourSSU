package com.example.blogyourssu.controller

import com.example.blog.dto.req.comment.CommentCreateReqDto
import com.example.blog.dto.req.comment.CommentDeleteReqDto
import com.example.blog.dto.req.comment.CommentUpdateReqDto
import com.example.blog.dto.res.comment.CommentCreateResDto
import com.example.blog.dto.res.comment.CommentUpdateResDto
import com.example.blogyourssu.service.CommentService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/comments")
class CommentController(private val commentService: CommentService) {

    @PostMapping("/create/{article_id}")
    fun create(@PathVariable article_id: Long?, @RequestBody dto: CommentCreateReqDto): CommentCreateResDto? {
        return article_id?.let { commentService.create(it, dto.content, dto.email, dto.password) }
    }

    @PutMapping("/update/{comment_id}")
    fun update(@PathVariable comment_id: Long?, @RequestBody dto: CommentUpdateReqDto): CommentUpdateResDto? {
        return comment_id?.let { commentService.update(it, dto.content, dto.email, dto.password) }
    }

    @DeleteMapping("/delete/{comment_id}")
    fun delete(@PathVariable comment_id: Long?, @RequestBody dto: CommentDeleteReqDto) {
        comment_id?.let { commentService.delete(it, dto.email, dto.password) }
    }


}