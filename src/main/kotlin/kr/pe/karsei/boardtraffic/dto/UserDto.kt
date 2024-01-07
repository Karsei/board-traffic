package kr.pe.karsei.boardtraffic.dto

import jakarta.annotation.Nonnull
import java.time.LocalDateTime

data class UserDto(
        val id: Long?,
        val userId: String?,
        val password: String?,
        val nickName: String?,
        val isAdmin: Boolean?,
        val createdAt: LocalDateTime?,
        val isWithDraw: Boolean?,
        val status: Status?,
        val updatedAt: LocalDateTime?,
) {
    enum class Status {
        DEFAULT, ADMIN, DELETED
    }

    companion object Register {
        fun hasNullDataBeforeRegister(userDto: UserRegisterRequest): Boolean {
            return userDto.userId == null ||
                    userDto.password == null ||
                    userDto.nickName == null
        }

        data class UserRegisterRequest(
                @Nonnull
                val userId: String,
                @Nonnull
                var password: String,
                @Nonnull
                val nickName: String,
        )
    }
}
