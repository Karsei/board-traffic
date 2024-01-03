package kr.pe.karsei.boardtraffic.service.impl

import kr.pe.karsei.boardtraffic.dto.UserDto
import kr.pe.karsei.boardtraffic.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl: UserService {
    override fun register(userProfile: UserDto): Void {
        TODO("Not yet implemented")
    }

    override fun login(id: String, password: String): UserDto {
        TODO("Not yet implemented")
    }

    override fun isDuplicatedId(id: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun getUserInfo(userId: String): UserDto {
        TODO("Not yet implemented")
    }

    override fun updatePassword(id: String, beforePassword: String, afterPassword: String): Void {
        TODO("Not yet implemented")
    }

    override fun deleteId(id: String, password: String): Void {
        TODO("Not yet implemented")
    }
}