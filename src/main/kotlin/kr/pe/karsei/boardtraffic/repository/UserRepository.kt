package kr.pe.karsei.boardtraffic.repository

import kr.pe.karsei.boardtraffic.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository: JpaRepository<User, Long> {
    fun findByUserId(userId: String): User?

    fun findByUserIdAndPassword(userId: String, password: String): User?
}