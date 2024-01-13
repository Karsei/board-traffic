package kr.pe.karsei.boardtraffic.service

import kr.pe.karsei.boardtraffic.dto.UserDto
import kr.pe.karsei.boardtraffic.entity.User
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
    override fun getUserInfo(userId: Long): UserDto {
        val user = (userLoadPort.getUserInfo(userId)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 아이디입니다."))
        return mapToEntityToDto(user)!!
    }

    @Transactional(readOnly = true)
    override fun login(accountId: String,
                       password: String): UserDto {
        val user = (userLoadPort.getUserInfo(accountId, password)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "올바르지 않는 아이디이거나 비밀번호입니다."))
        return mapToEntityToDto(user)!!
    }

    @Transactional
    override fun register(userProfile: UserDto.Register.UserRegisterRequest): UserDto {
        val user = userSavePort.register(userProfile)
        return mapToEntityToDto(user)!!
    }

    @Transactional
    override fun updatePassword(userId: Long,
                                beforePassword: String,
                                afterPassword: String): UserDto {
        val user = userSavePort.updatePassword(userId, beforePassword, afterPassword)
        return mapToEntityToDto(user)!!
    }

    @Transactional
    override fun deleteUser(userId: Long,
                            password: String): UserDto {
        val user = userSavePort.deleteUser(userId, password)
        return mapToEntityToDto(user)!!
    }

    private fun mapToEntityToDto(entity: User?): UserDto? {
        if (entity == null) return null
        return UserDto(
                id = entity.id,
                userId = entity.userId,
                password = entity.password,
                nickName = entity.nickName,
                isAdmin = entity.isAdmin,
                createdAt = entity.createdAt,
                isWithDraw = entity.isWithDraw,
                status = UserDto.Status.valueOf(entity.status.name),
                updatedAt = entity.updatedAt,
        )
    }
}