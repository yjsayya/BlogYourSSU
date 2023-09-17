package com.example.blogyourssu.service

import com.example.blog.domain.User
import com.example.blog.domain.User.Companion.of
import com.example.blog.dto.res.user.UserJoinResDto
import com.example.blog.error.BlogApplicationException
import com.example.blog.error.ErrorCode
import com.example.blogyourssu.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.regex.Pattern

@Service
open class UserService (

    private val userRepository: UserRepository,
    private val encoder: BCryptPasswordEncoder

) {

    /**
     * 회원가입
     */
    @Transactional
    open fun join(email: String?, password: String?, username: String?): UserJoinResDto {
        // 1. 아이디 중복 검사
        userRepository.findByEmail(email)
            ?.ifPresent { throw BlogApplicationException(ErrorCode.ALREADY_JOIN) }
        // 2. 이메일 형식 체크
        isValidEmail(email)
        // 3. 비밀번호 암호화
        val encryptPassword = encoder.encode(password)

        // 4. 회원가입 - 진행시켜
        val user = userRepository.save(
            of(email!!, encryptPassword!!, username!!)
        )

        return UserJoinResDto.entityToDto(user)
    }


    /**
     * 회원 탈퇴
     */
    fun deleteUser(email: String, password: String) {
        // 1. User 인증
        val user = validateUser(email, password)

        // 2. 회원 탈퇴 - 진행시켜
        userRepository.delete(user)
    }


    /**
     * User 인증
     */
    fun validateUser(email: String?, password: String?): User? {
        // 1. 아이디 체크
        val user = userRepository.findByEmail(email)?.orElseThrow {
            BlogApplicationException(ErrorCode.USER_NOT_FOUND)
        }
        // 2. 비밀번호 체크
        if (user != null) {
            if (!encoder.matches(password, user.password))
                throw BlogApplicationException(ErrorCode.INVALID_PASSWORD)
        }
        return user
    }

    /**
     * 이메일 형식 체크
     */
    private fun isValidEmail(email: String?) {
        val emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$"
        val pattern = Pattern.compile(emailRegex)
        if (email == null || !pattern.matcher(email)
                .matches()
        ) throw BlogApplicationException(ErrorCode.INVALID_EMAIL_FORMAT)
    }


}