package kr.pe.karsei.boardtraffic.port.`in`

import kr.pe.karsei.boardtraffic.dto.UserDto

interface UserUseCase {
    fun getUserInfo(userId: Long): UserDto

    fun login(accountId: String, password: String): UserDto

    fun register(userProfile: UserDto.Register.UserRegisterRequest): UserDto

    fun updatePassword(userId: Long, beforePassword: String, afterPassword: String): UserDto

    fun deleteUser(userId: Long, password: String): UserDto
}