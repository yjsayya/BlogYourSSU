package com.example.blogyourssu.error


class BlogApplicationException(val errorCode: ErrorCode, private val customMessage: String? = null) : RuntimeException() {

    override fun toString(): String {
        return if (customMessage == null) errorCode.message else "${errorCode.message}. $customMessage"
    }
}