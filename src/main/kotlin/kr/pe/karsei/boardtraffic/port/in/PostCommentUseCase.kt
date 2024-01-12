package kr.pe.karsei.boardtraffic.port.`in`

import kr.pe.karsei.boardtraffic.dto.CommentDto

interface PostCommentUseCase {
    fun insertComment(params: CommentDto.InsertCommentRequest)

    fun updateComment(commentId: Long, params: CommentDto.UpdateCommentRequest)

    fun deleteComment(commentId: Long)
}