package kr.pe.karsei.boardtraffic.dto

class CommentDto(
        val id: Long?,
        val contents: String?,
        val subCommentId: Long?
) {
    data class InsertCommentRequest(
        val contents: String,
        val subCommentId: Long?,
    )

    data class UpdateCommentRequest(
        val contents: String,
        val subCommentId: Long?,
    )
}