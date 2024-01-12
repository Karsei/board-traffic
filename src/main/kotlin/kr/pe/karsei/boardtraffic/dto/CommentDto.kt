package kr.pe.karsei.boardtraffic.dto

class CommentDto {
    data class InsertCommentRequest(
        val contents: String,
        val subCommentId: Long?,
    )

    data class UpdateCommentRequest(
        val contents: String,
        val subCommentId: Long?,
    )
}