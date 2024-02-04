package kr.pe.karsei.boardtraffic.application.post.service

import kr.pe.karsei.boardtraffic.application.post.dto.CommentDto
import kr.pe.karsei.boardtraffic.application.post.port.`in`.PostCommentUseCase
import kr.pe.karsei.boardtraffic.application.post.port.out.PostCommentSavePort
import kr.pe.karsei.boardtraffic.application.client.port.out.UserLoadPort
import kr.pe.karsei.boardtraffic.application.post.service.PostMapper.Companion.mapToEntityToPostCommentDto
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Service
class PostCommentService(
    private val postCommentSavePort: PostCommentSavePort,
    private val userLoadPort: UserLoadPort,
): PostCommentUseCase {
    @Transactional
    override fun insertComment(params: CommentDto.InsertCommentRequest): CommentDto {
        userLoadPort.getUserInfo(params.userId!!)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "유저가 존재하지 않습니다.")
        return mapToEntityToPostCommentDto(postCommentSavePort.insertComment(params))!!
    }

    @Transactional
    override fun updateComment(params: CommentDto.UpdateCommentRequest): CommentDto {
        userLoadPort.getUserInfo(params.userId!!)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "유저가 존재하지 않습니다.")
        return mapToEntityToPostCommentDto(postCommentSavePort.updateComment(params.commentId!!, params))!!
    }

    @Transactional
    override fun deleteComment(userId: Long, postId: Long, commentId: Long): CommentDto {
        userLoadPort.getUserInfo(userId)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "유저가 존재하지 않습니다.")
        return mapToEntityToPostCommentDto(postCommentSavePort.deleteComment(commentId))!!
    }
}