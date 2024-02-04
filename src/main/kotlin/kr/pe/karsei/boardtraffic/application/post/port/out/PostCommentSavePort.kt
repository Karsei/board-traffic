package kr.pe.karsei.boardtraffic.application.post.port.out

import kr.pe.karsei.boardtraffic.application.post.dto.CommentDto
import kr.pe.karsei.boardtraffic.application.post.domain.Comment

interface PostCommentSavePort {
    fun insertComment(params: CommentDto.InsertCommentRequest): Comment

    fun updateComment(commentId: Long, params: CommentDto.UpdateCommentRequest): Comment

    fun deleteComment(commentId: Long): Comment
}