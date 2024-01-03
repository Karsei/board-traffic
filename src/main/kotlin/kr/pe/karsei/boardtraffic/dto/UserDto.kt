package kr.pe.karsei.boardtraffic.dto

import java.util.Date

data class UserDto(
        val id: Int,
        val userId: String,
        val password: String,
        val nickName: String,
        val isAdmin: Boolean,
        val createdAt: Date,
        val isWithDraw: Boolean,
        val status: Status,
        val updatedAt: Date,
) {
    enum class Status {
        DEFAULT, ADMIN, DELETED
    }
}
