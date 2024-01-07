package kr.pe.karsei.boardtraffic.dto.request

import jakarta.annotation.Nonnull

class UserUpdatePasswordRequest(
        @Nonnull
        val beforePassword: String,
        @Nonnull
        val afterPassword: String,
)