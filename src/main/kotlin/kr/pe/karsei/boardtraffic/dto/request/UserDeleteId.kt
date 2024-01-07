package kr.pe.karsei.boardtraffic.dto.request

import jakarta.annotation.Nonnull

class UserDeleteId(
        @Nonnull
        private val id: String,
        @Nonnull
        private val password: String,
) {
}