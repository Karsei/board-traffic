package kr.pe.karsei.boardtraffic.application.client.port.out

import kr.pe.karsei.boardtraffic.application.client.domain.User

interface UserLoadPort {
    fun getUserInfo(userId: Long): User?

    fun getUserInfo(accountId: String, password: String): User?
}