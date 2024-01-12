package kr.pe.karsei.boardtraffic.port.out

import kr.pe.karsei.boardtraffic.dto.CommentDto

interface PostCommentSavePort {
    fun insertComment(params: CommentDto.InsertCommentRequest)

    fun updateComment(commentId: Long, params: CommentDto.UpdateCommentRequest)

    fun deleteComment(commentId: Long)
}