package kr.pe.karsei.boardtraffic.application.client.service

import kr.pe.karsei.boardtraffic.application.client.dto.UserDto
import kr.pe.karsei.boardtraffic.application.client.exception.NotFoundClientException
import kr.pe.karsei.boardtraffic.application.client.exception.NotFoundOrInvalidPasswordClientException
import kr.pe.karsei.boardtraffic.application.client.port.`in`.UserUseCase
import kr.pe.karsei.boardtraffic.application.client.port.out.UserLoadPort
import kr.pe.karsei.boardtraffic.application.client.port.out.UserSavePort
import kr.pe.karsei.boardtraffic.application.client.service.UserMapper.Companion.mapToEntityToDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userLoadPort: UserLoadPort,
    private val userSavePort: UserSavePort,
): UserUseCase {
    @Transactional(readOnly = true)
    override fun getUserInfo(userId: Long): UserDto {
        val user = (userLoadPort.getUserInfo(userId)
                ?: throw NotFoundClientException())
        return mapToEntityToDto(user)!!
    }

    @Transactional(readOnly = true)
    override fun login(accountId: String,
                       password: String): UserDto {
        val user = (userLoadPort.getUserInfo(accountId, password)
                ?: throw NotFoundOrInvalidPasswordClientException())
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
}