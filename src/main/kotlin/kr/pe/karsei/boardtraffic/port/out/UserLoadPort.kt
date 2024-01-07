package kr.pe.karsei.boardtraffic.port.out

import kr.pe.karsei.boardtraffic.dto.UserDto

interface UserLoadPort {
    fun getUserInfo(userId: String): UserDto?

    fun getUserInfo(userId: String, password: String): UserDto?
}