package kr.pe.karsei.boardtraffic.repository

import kr.pe.karsei.boardtraffic.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    fun findByUserId(userId: String): User?

    fun findByIdAndPassword(id: Long, password: String): User?

    fun findByUserIdAndPassword(userId: String, password: String): User?
}