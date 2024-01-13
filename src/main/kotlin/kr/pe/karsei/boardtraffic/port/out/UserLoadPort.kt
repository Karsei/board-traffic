package kr.pe.karsei.boardtraffic.port.out

import kr.pe.karsei.boardtraffic.entity.User

interface UserLoadPort {
    fun getUserInfo(userId: Long): User?

    fun getUserInfo(accountId: String, password: String): User?
}