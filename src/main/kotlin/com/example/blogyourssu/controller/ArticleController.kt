package com.example.blogyourssu.controller

import com.example.blog.dto.req.article.ArticleCreateReqDto
import com.example.blog.dto.req.article.ArticleDeleteReqDto
import com.example.blog.dto.req.article.ArticleUpdateReqDto
import com.example.blog.dto.res.article.ArticleCreateResDto
import com.example.blog.dto.res.article.ArticleUpdateResDto
import com.example.blogyourssu.service.ArticleService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/articles")
class ArticleController(private val articleService: ArticleService) {

    @PostMapping("/create")
    fun create(@RequestBody dto: ArticleCreateReqDto): ArticleCreateResDto {
        return articleService.create(dto.title, dto.content, dto.email, dto.password)
    }


    @PutMapping("/update/{article_id}")
    fun update(@PathVariable article_id: Long?, @RequestBody dto: ArticleUpdateReqDto): ArticleUpdateResDto? {
        val title = dto.title
        val content = dto.content
        val email = dto.email
        val password = dto.password
        return article_id?.let { articleService.update(it, title, content, email, password) }
    }

    @DeleteMapping("/delete/{article_id}")
    fun delete(@PathVariable article_id: Long?, @RequestBody dto: ArticleDeleteReqDto) {
        if (article_id != null) {
            articleService.delete(article_id, dto.email, dto.password)
        }
    }


}