package kr.pe.karsei.boardtraffic.application.client.dto.request

import jakarta.annotation.Nonnull

class UserDeleteRequest(
        @Nonnull
        val userId: String,
        @Nonnull
        val password: String,
) {
}