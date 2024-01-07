package kr.pe.karsei.boardtraffic.port.`in`

import kr.pe.karsei.boardtraffic.dto.UserDto

interface UserUseCase {
    fun getUserInfo(userId: String): UserDto

    fun login(userId: String, password: String): UserDto

    fun register(userProfile: UserDto)

    fun updatePassword(userId: String, beforePassword: String, afterPassword: String)

    fun deleteUser(userId: String, password: String)
}