package kr.pe.karsei.boardtraffic.application.post.service

import kr.pe.karsei.boardtraffic.application.client.exception.NotFoundClientException
import kr.pe.karsei.boardtraffic.application.post.dto.CommentDto
import kr.pe.karsei.boardtraffic.application.post.port.`in`.PostCommentUseCase
import kr.pe.karsei.boardtraffic.application.post.port.out.PostCommentSavePort
import kr.pe.karsei.boardtraffic.application.client.port.out.UserLoadPort
import kr.pe.karsei.boardtraffic.application.post.service.PostMapper.Companion.mapToEntityToPostCommentDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostCommentService(
    private val postCommentSavePort: PostCommentSavePort,
    private val userLoadPort: UserLoadPort,
): PostCommentUseCase {
    @Transactional
    override fun insertComment(params: CommentDto.InsertCommentRequest): CommentDto {
        userLoadPort.getUserInfo(params.userId!!)
                ?: throw NotFoundClientException()
        return mapToEntityToPostCommentDto(postCommentSavePort.insertComment(params))!!
    }

    @Transactional
    override fun updateComment(params: CommentDto.UpdateCommentRequest): CommentDto {
        userLoadPort.getUserInfo(params.userId!!)
                ?: throw NotFoundClientException()
        return mapToEntityToPostCommentDto(postCommentSavePort.updateComment(params.commentId!!, params))!!
    }

    @Transactional
    override fun deleteComment(userId: Long, postId: Long, commentId: Long): CommentDto {
        userLoadPort.getUserInfo(userId)
                ?: throw NotFoundClientException()
        return mapToEntityToPostCommentDto(postCommentSavePort.deleteComment(commentId))!!
    }
}