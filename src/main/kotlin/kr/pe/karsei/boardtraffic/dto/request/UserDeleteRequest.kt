package kr.pe.karsei.boardtraffic.dto.request

import jakarta.annotation.Nonnull

class UserDeleteRequest(
        @Nonnull
        val id: String,
        @Nonnull
        val password: String,
) {
}