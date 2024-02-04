package kr.pe.karsei.boardtraffic.application.post.service

import kr.pe.karsei.boardtraffic.application.post.dto.CommentDto
import kr.pe.karsei.boardtraffic.application.post.domain.Comment
import kr.pe.karsei.boardtraffic.application.fixture.UserMock
import kr.pe.karsei.boardtraffic.application.post.port.out.PostCommentSavePort
import kr.pe.karsei.boardtraffic.application.client.port.out.UserLoadPort
import org.junit.jupiter.api.Test

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.given
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

@ExtendWith(MockitoExtension::class)
class PostCommentServiceMockTest {
    @Mock
    private lateinit var postCommentSavePort: PostCommentSavePort
    @Mock
    private lateinit var userLoadPort: UserLoadPort
    @InjectMocks
    private lateinit var postCommentService: PostCommentService

    @Test
    fun testAddingComment() {
        // given
        given(userLoadPort.getUserInfo(anyLong())).willReturn(UserMock.createUser())

        val argCapture = argumentCaptor<CommentDto.InsertCommentRequest>()
        given(postCommentSavePort.insertComment(argCapture.capture())).willReturn(Comment(1L, "댓글"))

        // when
        val request = CommentDto.InsertCommentRequest(1L, 1L, "댓글", null)
        val result = postCommentService.insertComment(request)

        // then
        assertAll(
            { assertThat(result).isNotNull() },
            { assertThat(result.id).isEqualTo(1L) },
            { assertThat(result.contents).isEqualTo("댓글") },
        )
    }

    @Test
    fun testAddingCommentWithUserNotFound() {
        // given
        given(userLoadPort.getUserInfo(anyLong())).willReturn(null)

        // when & then
        val request = CommentDto.InsertCommentRequest(1L, 1L, "댓글", null)
        val result = assertThrows(ResponseStatusException::class.java) { postCommentService.insertComment(request) }
        assertAll(
            { assertThat(result).isNotNull() },
            { assertThat(result.reason).isEqualTo("유저가 존재하지 않습니다.") },
            { assertThat(result.statusCode).isEqualTo(HttpStatus.NOT_FOUND) },
        )
    }

    @Test
    fun updateComment() {
        // given
        given(userLoadPort.getUserInfo(anyLong())).willReturn(UserMock.createUser())

        val argLong = argumentCaptor<Long>()
        val argUpdate = argumentCaptor<CommentDto.UpdateCommentRequest>()
        given(postCommentSavePort.updateComment(argLong.capture(), argUpdate.capture())).willReturn(Comment(1L, "댓글"))

        // when
        val request = CommentDto.UpdateCommentRequest(1L, 1L, 1L, "댓글", null)
        val result = postCommentService.updateComment(request)

        // then
        assertAll(
            { assertThat(result).isNotNull() },
            { assertThat(result.id).isEqualTo(1L) },
            { assertThat(result.contents).isEqualTo("댓글") },
        )
    }

    @Test
    fun updateCommentWithNotFound() {
        // given
        given(userLoadPort.getUserInfo(anyLong())).willReturn(null)

        // when & then
        val request = CommentDto.UpdateCommentRequest(1L, 1L, 1L, "댓글", null)
        val result = assertThrows(ResponseStatusException::class.java) { postCommentService.updateComment(request) }
        assertAll(
            { assertThat(result).isNotNull() },
            { assertThat(result.reason).isEqualTo("유저가 존재하지 않습니다.") },
            { assertThat(result.statusCode).isEqualTo(HttpStatus.NOT_FOUND) },
        )
    }

    @Test
    fun deleteComment() {
        // given
        given(userLoadPort.getUserInfo(anyLong())).willReturn(UserMock.createUser())

        val argLong = argumentCaptor<Long>()
        given(postCommentSavePort.deleteComment(argLong.capture())).willReturn(Comment(1L, "댓글"))

        // when
        val result = postCommentService.deleteComment(1L, 1L, 1L)

        // then
        assertAll(
            { assertThat(result).isNotNull() },
            { assertThat(result.id).isEqualTo(1L) },
            { assertThat(result.contents).isEqualTo("댓글") },
        )
    }

    @Test
    fun deleteCommentWithNotFound() {
        // given
        given(userLoadPort.getUserInfo(anyLong())).willReturn(null)

        // when & then
        val userId = 1L
        val postId = 1L
        val commentId = 1L
        val result = assertThrows(ResponseStatusException::class.java) { postCommentService.deleteComment(userId, postId, commentId) }
        assertAll(
            { assertThat(result).isNotNull() },
            { assertThat(result.reason).isEqualTo("유저가 존재하지 않습니다.") },
            { assertThat(result.statusCode).isEqualTo(HttpStatus.NOT_FOUND) },
        )
    }
}