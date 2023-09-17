package com.example.blog.error

class BlogApplicationException(val errorCode: ErrorCode) : RuntimeException() {

    private val _message: String? = null

    fun getMessage(): String {
        return if (_message == null) errorCode.message else "${errorCode.message}. $_message"
    }

}