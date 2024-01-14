package kr.pe.karsei.boardtraffic.dto

import java.time.LocalDateTime

data class PostDto(
    val id: Long?,
    val title: String?,
    val isAdmin: Boolean?,
    val contents: String?,
    val views: Int?,
    val categoryId: Long?,
    val userId: Long?,
    val fileId: Long?,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?,
) {
    data class PostSearchRequest(
        val title: String? = null,
        val contents: String? = null,
        val categoryId: Long? = null,
        val sortStatus: SortStatus? = null,
    ) {
        enum class SortStatus {
            NEWEST, OLDEST, CATEGORIES
        }
    }

    data class InsertPostRequest(
            var userId: Long?,
            var fileId: Long?,
            val title: String,
            val contents: String,
            val categoryId: Long,
    ) {
        fun setMandatory(userId: Long) {
            this.userId = userId
        }
    }

    data class UpdatePostRequest(
        var userId: Long?,
        var postId: Long?,
        var categoryId: Long?,
        var fileId: Long?,
        val id: Long,
        val title: String,
        val contents: String,
        val views: Int,
    ) {
        fun setMandatory(userId: Long, postId: Long) {
            this.userId = userId
            this.postId = postId
        }
    }
}