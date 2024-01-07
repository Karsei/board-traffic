package kr.pe.karsei.boardtraffic.service

import kr.pe.karsei.boardtraffic.dto.UserDto
import kr.pe.karsei.boardtraffic.port.`in`.UserUseCase
import kr.pe.karsei.boardtraffic.port.out.UserLoadPort
import kr.pe.karsei.boardtraffic.port.out.UserSavePort
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Service
class UserService(
        private val userLoadPort: UserLoadPort,
        private val userSavePort: UserSavePort,
): UserUseCase {
    @Transactional(readOnly = true)
    override fun getUserInfo(userId: String): UserDto {
        return userLoadPort.getUserInfo(userId)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 아이디입니다.")
    }

    @Transactional(readOnly = true)
    override fun login(userId: String,
                       password: String): UserDto {
        return userLoadPort.getUserInfo(userId, password)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "올바르지 않는 아이디이거나 비밀번호입니다.")

    }

    @Transactional
    override fun register(userProfile: UserDto) {
        userSavePort.register(userProfile)
    }

    @Transactional
    override fun updatePassword(userId: String,
                                beforePassword: String,
                                afterPassword: String) {
        userSavePort.updatePassword(userId, beforePassword, afterPassword)
    }

    @Transactional
    override fun deleteUser(userId: String,
                            password: String) {
        userSavePort.deleteId(userId, password)
    }
}