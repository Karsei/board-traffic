package kr.pe.karsei.boardtraffic.adapter.`in`

import jakarta.servlet.http.HttpSession
import kr.pe.karsei.boardtraffic.aop.LoginCheck
import kr.pe.karsei.boardtraffic.dto.UserDto
import kr.pe.karsei.boardtraffic.dto.request.UserDeleteRequest
import kr.pe.karsei.boardtraffic.dto.request.UserLoginRequest
import kr.pe.karsei.boardtraffic.dto.request.UserUpdatePasswordRequest
import kr.pe.karsei.boardtraffic.dto.response.LoginResponse
import kr.pe.karsei.boardtraffic.dto.response.UserInfoResponse
import kr.pe.karsei.boardtraffic.port.`in`.UserUseCase
import kr.pe.karsei.boardtraffic.util.SessionUtil
import kr.pe.karsei.boardtraffic.util.logger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/users")
class UserController(
        private val userUseCase: UserUseCase,
) {
    val logger = logger()

    @GetMapping("my-info")
    fun memberInfo(session: HttpSession): UserInfoResponse {
        val id = SessionUtil.getLoginMemberId(session)

        val userInfo = userUseCase.getUserInfo(id!!)
        return UserInfoResponse(userInfo)
    }

    @PostMapping("sign-in")
    fun signIn(@RequestBody userLoginRequest: UserLoginRequest,
               httpSession: HttpSession): UserDto {
        val id = userLoginRequest.userId
        val password = userLoginRequest.password

        val userDto = userUseCase.login(id, password)
        if (userDto.status == UserDto.Status.ADMIN)
            SessionUtil.setLoginAdminId(httpSession, id)
        else
            SessionUtil.setLoginMemberId(httpSession, id)

        return userDto
    }

    @PutMapping
    fun logout(accountId: String,
               session: HttpSession) {
        SessionUtil.clear(session)
    }

    @PostMapping("sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    fun signUp(@RequestBody userDto: UserDto.Register.UserRegisterRequest) {
        if (UserDto.hasNullDataBeforeRegister(userDto))
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "회원가입 정보를 확인해주세요.")

        userUseCase.register(userDto)
    }

    @PatchMapping("password")
    @LoginCheck(type = LoginCheck.UserType.USER)
    fun updateUserPassword(accountId: String,
                           @RequestBody userUpdatePasswordRequest: UserUpdatePasswordRequest,
                           session: HttpSession): ResponseEntity<out Any> {
        val id: String = accountId
        val beforePassword: String = userUpdatePasswordRequest.beforePassword
        val afterPassword: String = userUpdatePasswordRequest.afterPassword

        return try {
            userUseCase.updatePassword(id, beforePassword, afterPassword)
            ResponseEntity(HttpStatus.NO_CONTENT)
        } catch (e: IllegalArgumentException) {
            logger.error("updatePassword 실패", e)
            FAIL_RESPONSE
        }
    }

    @DeleteMapping
    fun deleteId(@RequestBody userDeleteRequest: UserDeleteRequest,
                 session: HttpSession
    ): ResponseEntity<out Any> {
        return try {
            userUseCase.deleteUser(SessionUtil.getLoginMemberId(session)!!, userDeleteRequest.password)
            ResponseEntity(HttpStatus.NO_CONTENT)
        } catch (e: RuntimeException) {
            logger.info("deleteID 실패", e)
            FAIL_RESPONSE
        }
    }

    companion object {
        private val FAIL_RESPONSE = ResponseEntity<LoginResponse>(HttpStatus.BAD_REQUEST)
    }
}