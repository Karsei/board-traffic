package kr.pe.karsei.boardtraffic.dto

import java.time.LocalDateTime

data class PostDto(
    val id: Long?,
    val title: String?,
    val isAdmin: Boolean?,
    val contents: String?,
    val views: Int,
    val categoryId: Long?,
    val userId: Long?,
    val fileId: Long?,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?,
) {
    companion object Search {
        data class PostSearchRequest(
            val title: String? = null,
            val contents: String? = null,
            val categoryId: Long? = null,
            val sortStatus: SortStatus? = null,
        )

        enum class SortStatus {
            NEWEST, OLDEST, CATEGORIES
        }
    }
}