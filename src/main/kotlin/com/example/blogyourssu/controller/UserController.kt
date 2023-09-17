package com.example.blogyourssu.controller


import com.example.blogyourssu.dto.req.user.UserJoinReqDto
import com.example.blogyourssu.dto.req.user.UserWithdrawReqDto
import com.example.blogyourssu.dto.res.user.UserJoinResDto
import com.example.blogyourssu.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @PostMapping("/join")
    fun join(@RequestBody dto: UserJoinReqDto): UserJoinResDto {
        return userService.join(dto.email, dto.password, dto.username)
    }

    @DeleteMapping("/withdraw")
    fun deleteUser(@RequestBody dto: UserWithdrawReqDto) {
        dto.email?.let { email ->
            dto.password?.let { password ->
                userService.deleteUser(email, password)
            }
        }
    }


}