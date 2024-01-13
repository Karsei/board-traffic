package kr.pe.karsei.boardtraffic.service.post

import kr.pe.karsei.boardtraffic.dto.CommentDto
import kr.pe.karsei.boardtraffic.port.`in`.PostCommentUseCase
import kr.pe.karsei.boardtraffic.port.out.PostCommentSavePort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostCommentService(
    private val postCommentSavePort: PostCommentSavePort,
): PostCommentUseCase {
    @Transactional
    override fun insertComment(params: CommentDto.InsertCommentRequest) {
        return postCommentSavePort.insertComment(params)
    }

    @Transactional
    override fun updateComment(commentId: Long, params: CommentDto.UpdateCommentRequest) {
        return postCommentSavePort.updateComment(commentId, params)
    }

    @Transactional
    override fun deleteComment(commentId: Long) {
        return postCommentSavePort.deleteComment(commentId)
    }
}