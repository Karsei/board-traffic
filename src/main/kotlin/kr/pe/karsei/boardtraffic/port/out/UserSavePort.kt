package kr.pe.karsei.boardtraffic.port.out

import kr.pe.karsei.boardtraffic.dto.UserDto

interface UserSavePort {
    fun register(userProfile: UserDto.Register.UserRegisterRequest): UserDto

    fun updatePassword(userId: String,
                       beforePassword: String,
                       afterPassword: String)

    fun deleteUser(userId: String,
                   password: String)
}