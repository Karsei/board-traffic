package kr.pe.karsei.boardtraffic.dto.request

import jakarta.annotation.Nonnull

class UserLoginRequest(
        @Nonnull
        val userId: String,
        @Nonnull
        val password: String,
)