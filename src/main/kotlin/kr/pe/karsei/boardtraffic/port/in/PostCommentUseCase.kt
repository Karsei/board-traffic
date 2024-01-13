package kr.pe.karsei.boardtraffic.port.`in`

import kr.pe.karsei.boardtraffic.dto.CommentDto

interface PostCommentUseCase {
    fun insertComment(userId: Long, postId: Long, params: CommentDto.InsertCommentRequest): CommentDto

    fun updateComment(userId: Long, postId: Long, commentId: Long, params: CommentDto.UpdateCommentRequest): CommentDto

    fun deleteComment(userId: Long, postId: Long, commentId: Long): CommentDto
}