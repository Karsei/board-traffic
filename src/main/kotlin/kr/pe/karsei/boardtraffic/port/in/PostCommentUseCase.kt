package kr.pe.karsei.boardtraffic.port.`in`

import kr.pe.karsei.boardtraffic.dto.CommentDto

interface PostCommentUseCase {
    fun insertComment(params: CommentDto.InsertCommentRequest): CommentDto

    fun updateComment(params: CommentDto.UpdateCommentRequest): CommentDto

    fun deleteComment(userId: Long, postId: Long, commentId: Long): CommentDto
}