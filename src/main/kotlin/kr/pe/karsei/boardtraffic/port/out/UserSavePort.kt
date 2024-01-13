package kr.pe.karsei.boardtraffic.port.out

import kr.pe.karsei.boardtraffic.dto.UserDto
import kr.pe.karsei.boardtraffic.entity.User

interface UserSavePort {
    fun register(userProfile: UserDto.Register.UserRegisterRequest): User

    fun updatePassword(userId: Long,
                       beforePassword: String,
                       afterPassword: String): User

    fun deleteUser(userId: Long,
                   password: String): User
}