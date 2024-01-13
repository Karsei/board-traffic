package kr.pe.karsei.boardtraffic.port.out

import kr.pe.karsei.boardtraffic.dto.CommentDto
import kr.pe.karsei.boardtraffic.entity.Comment

interface PostCommentSavePort {
    fun insertComment(params: CommentDto.InsertCommentRequest): Comment

    fun updateComment(commentId: Long, params: CommentDto.UpdateCommentRequest): Comment

    fun deleteComment(commentId: Long): Comment
}