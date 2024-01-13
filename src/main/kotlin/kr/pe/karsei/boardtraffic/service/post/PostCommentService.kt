package kr.pe.karsei.boardtraffic.service.post

import kr.pe.karsei.boardtraffic.dto.CommentDto
import kr.pe.karsei.boardtraffic.port.`in`.PostCommentUseCase
import kr.pe.karsei.boardtraffic.port.out.PostCommentSavePort
import kr.pe.karsei.boardtraffic.port.out.UserLoadPort
import kr.pe.karsei.boardtraffic.service.post.PostMapper.Companion.mapToEntityToPostCommentDto
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
    override fun insertComment(userId: Long, postId: Long, params: CommentDto.InsertCommentRequest): CommentDto {
        userLoadPort.getUserInfo(userId)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "유저가 존재하지 않습니다.")
        return mapToEntityToPostCommentDto(postCommentSavePort.insertComment(params))!!
    }

    @Transactional
    override fun updateComment(userId: Long, postId: Long, commentId: Long, params: CommentDto.UpdateCommentRequest): CommentDto {
        userLoadPort.getUserInfo(userId)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "유저가 존재하지 않습니다.")
        return mapToEntityToPostCommentDto(postCommentSavePort.updateComment(commentId, params))!!
    }

    @Transactional
    override fun deleteComment(userId: Long, postId: Long, commentId: Long): CommentDto {
        userLoadPort.getUserInfo(userId)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "유저가 존재하지 않습니다.")
        return mapToEntityToPostCommentDto(postCommentSavePort.deleteComment(commentId))!!
    }
}