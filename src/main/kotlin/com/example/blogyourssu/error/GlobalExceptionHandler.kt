package com.example.blog.error

import com.example.blog.dto.ErrorResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import java.time.LocalDateTime

@RestControllerAdvice
class GlobalExceptionHandler {

    companion object {
        private val log = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)
    }

    @ExceptionHandler(BlogApplicationException::class)
    fun errorHandler(e: BlogApplicationException, request: WebRequest): ResponseEntity<ErrorResponse> {
        log.error("Error occurs {}", e.toString())

        val errorResponse = ErrorResponse(
            LocalDateTime.now(),
            e.errorCode.httpStatus,
            e.message,
            request.getDescription(false)
        )


        return ResponseEntity(errorResponse, e.errorCode.httpStatus ?: HttpStatus.BAD_REQUEST)

    }
}