package kr.pe.karsei.boardtraffic.application.client.port.out

import kr.pe.karsei.boardtraffic.application.client.dto.UserDto
import kr.pe.karsei.boardtraffic.application.client.domain.User

interface UserSavePort {
    fun register(userProfile: UserDto.Register.UserRegisterRequest): User

    fun updatePassword(userId: Long,
                       beforePassword: String,
                       afterPassword: String): User

    fun deleteUser(userId: Long,
                   password: String): User
}