package kr.pe.karsei.boardtraffic.service

import kr.pe.karsei.boardtraffic.dto.UserDto

interface UserService {
    fun register(userProfile: UserDto): Void

    fun login(id: String, password: String): UserDto

    fun isDuplicatedId(id: String): Boolean

    fun getUserInfo(userId: String): UserDto

    fun updatePassword(id: String, beforePassword: String, afterPassword: String): Void

    fun deleteId(id: String, password: String): Void
}