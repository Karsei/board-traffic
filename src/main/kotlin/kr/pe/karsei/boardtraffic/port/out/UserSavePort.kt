package kr.pe.karsei.boardtraffic.port.out

import kr.pe.karsei.boardtraffic.dto.UserDto

interface UserSavePort {
    fun register(userProfile: UserDto.Companion.UserRegisterRequest): UserDto

    fun updatePassword(userId: String,
                       beforePassword: String,
                       afterPassword: String)

    fun deleteId(userId: String,
                 password: String)
}