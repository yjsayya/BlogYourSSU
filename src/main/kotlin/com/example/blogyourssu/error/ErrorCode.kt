package com.example.blog.error

import org.springframework.http.HttpStatus

enum class ErrorCode(val httpStatus: HttpStatus, val message: String) {
    // user
    ALREADY_JOIN(HttpStatus.CONFLICT, "Your Account Already Exists"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "No Such User"),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "Invalid Password"),
    INVALID_EMAIL_FORMAT(HttpStatus.BAD_REQUEST, "Invalid Email Format"),

    // article
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "No Such Article"),
    EMPTY_ARTICLE_TITLE(HttpStatus.BAD_REQUEST, "Article's Title Is Empty"),
    EMPTY_ARTICLE_CONTENT(HttpStatus.BAD_REQUEST, "Article's Content Is Empty"),
    ARTICLE_PERMISSION_DENIED(HttpStatus.BAD_REQUEST, "Permission Denied"),

    // comment
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "No Such Comment"),
    EMPTY_COMMENT(HttpStatus.BAD_REQUEST, "Comment Is Empty"),
    COMMENT_PERMISSION_DENIED(HttpStatus.BAD_REQUEST, "Permission Denied");

}